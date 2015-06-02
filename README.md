#TL;DR
The architecture employs MVP and CQRS patterns. RxJava is used to bind layers, provide asynchrony and make async calls testable.

#Problem
The usual DDDD (Deadline Driven Development or Die; also known as 4D) approach has lead to non-structural and not-testable codebase with growing complexity. 
All code belongs to one module. Communication with API and business is performed within UI code. Code is not testable due to coupling with external services and the platform.

#Requirements
Application code shold be divided into separate layers which don't depend on each other.
Layers should be easily mocked and/or changed with another implementation. API business logic should not depend on what API is used to retrieve data.
Business logic has to be easily unit tested.
Responsibilities between components should be clearly defined.

#Solution
MVP, CQRS, Rx
![Onion structure](http://i284.photobucket.com/albums/ll17/Vlado_Atanasov/OnionNew_zpsazui7lqd.jpg)

#Project Structure

##Entity
POJO business entities shared accros the app.

##Data
Communication with external services (REST API, Couchbase) goes to the data package. All models wich belong to specifics of API data structure stay here.

###Example:
FlightInfoService implements communication with REST API.
It uses FlightRequest and FlightResponse classes to map API data structure.

##Repository
Repositories handles data retrieval from external services and mapping to common entities.

##Domain
Business logic is implemented in form of separate command/query classes for each action.
Instead of having one StationController/Util/Service with methods like getAllStations, getStationByName, getNearbyStations, there will be separate class for each method.

###Example:
```java
package com.ryanair.cheapflights.domain.station;
public class GetNearbyStation {
    public GetNearbyStation(StationRepository repository) {
        // ...
    }
    public Observable<Station> execute(Location currentUserLocation) {
        // find stations in 250km radius
    }
}
```

##Presentation
Holds view* interfaces and presenters** (see MVP pattern).
*The view is a passive interface that displays data (the model) and routes user commands (events) to the presenter to act upon that data.
**The presenter acts upon the model and the view. It retrieves data from the model, and formats it for display in the view.

##App
All platform related code. Implementation of views. Instantiating API services and providing endpoint. Legacy code is kept here as well.

##Dependency Injection
Dagger 2

##Reactive
Code across the app is bound by RxJava's Observable class. Using reactive approach allows to write asynchrony-enabled code on each level of abstraction and leave background threading and asynchrony support to the final consumer of the data.
RxJava also allows to write unit tests against asynchronous code without changing implementation, workarounds or any kind of threading synchronisation code. Basically there out-of-the-box ability to convert async result into synchronous feed.
Repositories map and return data reactive way.
```java
public class FlightInfoRepositoryDotRez {
	public FlightInfoRepositoryDotRez(FlightInfoService service) {
		flightInfoService = service;
	}

	public Observalbe<FligthInfo> getByRoute(String from, String to) {
        FlightInfoRequest request = new FlightInfoRequest(from, to);

        return service.getFlightInfo(request)
                .map(new Func1<FlightInfoResponse, List<FlightInfo>>() {
                    @Override
                    public List<FlightInfo> call(FlightInfoResponse response) {
                        List<FlightInfo> results = new ArrayList<>();
                        
                        // map to FlightInfo

                        return results;
                    }
        });
	}
}
```

Business logic consumes observable collections, apply business rules and return results in form of reactive observable result.
The result of the work above goes to presenters where reactive data being applied to views in asynchronous way.

##Testing
JUnit

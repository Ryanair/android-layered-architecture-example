package com.ryanair.cheapflights.di;

import com.ryanair.cheapflights.data.api.dotRez.Api;
import com.ryanair.cheapflights.data.api.dotRez.service.FlightInfoService;
import com.ryanair.cheapflights.domain.command.GetFlightInfo;
import com.ryanair.cheapflights.presentation.presenter.FlightInfoResultsPresenter;
import com.ryanair.cheapflights.repository.FlightInfoRepository;
import com.ryanair.cheapflights.ui.flightinformation.FlightInfoResultsActivity;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = FlightInfoResultsActivity.class
)
public class FlightInfoResultsModule {
    private final FlightInfoResultsActivity flightInfoResultsActivity;

    public FlightInfoResultsModule(FlightInfoResultsActivity activity) {
        this.flightInfoResultsActivity = activity;
    }

    @Provides
    public FlightInfoResultsPresenter provideFlightInfoSearchPresenter() {
        FlightInfoService service = Api.create(FlightInfoService.class);
        FlightInfoRepository repository = new FlightInfoRepository(service);
        GetFlightInfo getFlightInfo = new GetFlightInfo(repository);
        return new FlightInfoResultsPresenter(flightInfoResultsActivity, getFlightInfo);
    }
}

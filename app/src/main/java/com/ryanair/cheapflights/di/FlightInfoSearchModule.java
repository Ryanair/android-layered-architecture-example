package com.ryanair.cheapflights.di;

import com.ryanair.cheapflights.presentation.presenter.FlightInfoSearchPresenter;
import com.ryanair.cheapflights.ui.flightinformation.FlightInfoSearchActivity;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = FlightInfoSearchActivity.class
)
public class FlightInfoSearchModule {
    private final FlightInfoSearchActivity flightInfoSearchActivity;

    public FlightInfoSearchModule(FlightInfoSearchActivity activity) {
        this.flightInfoSearchActivity = activity;
    }

    @Provides
    public FlightInfoSearchPresenter provideFlightInfoSearchPresenter() {
        return new FlightInfoSearchPresenter(flightInfoSearchActivity);
    }
}
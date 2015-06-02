package com.ryanair.cheapflights.presentation.presenter;

import com.ryanair.cheapflights.presentation.view.FlightInfoSearchView;

public class FlightInfoSearchPresenter implements Presenter {
    private final FlightInfoSearchView view;

    public FlightInfoSearchPresenter(FlightInfoSearchView view) {
        this.view = view;
    }

    public void search() {
        view.goToSearchResults(view.getFlightNumber());
    }
}

package com.ryanair.cheapflights.presentation.presenter;

import com.ryanair.cheapflights.domain.command.GetFlightInfo;
import com.ryanair.cheapflights.entity.FlightInfo;
import com.ryanair.cheapflights.presentation.view.FlightInfoResultsView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FlightInfoResultsPresenter implements Presenter {
    private final FlightInfoResultsView view;
    private final GetFlightInfo getFlightInfo;

    public FlightInfoResultsPresenter(FlightInfoResultsView view, GetFlightInfo getFlightInfo) {
        this.view = view;
        this.getFlightInfo = getFlightInfo;
    }

    public void startSearch() {
        searchFlightInfo(view.getFlightNumber());
    }

    public void selectFlight(FlightInfo flightInfo) {
        view.displaySelectedFlight(flightInfo);
    }

    private void searchFlightInfo(String flight) {
        getFlightInfo.execute(flight)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<FlightInfo>>() {
                    @Override
                    public void call(List<FlightInfo> flightInfoList) {
                        view.displayFlightInfo(flightInfoList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.displayError(throwable);
                    }
                });
    }
}
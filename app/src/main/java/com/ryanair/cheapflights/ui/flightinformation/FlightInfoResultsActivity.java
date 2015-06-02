package com.ryanair.cheapflights.ui.flightinformation;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ryanair.cheapflights.R;
import com.ryanair.cheapflights.di.FlightInfoResultsModule;
import com.ryanair.cheapflights.entity.FlightInfo;
import com.ryanair.cheapflights.presentation.presenter.FlightInfoResultsPresenter;
import com.ryanair.cheapflights.presentation.view.FlightInfoResultsView;
import com.ryanair.cheapflights.ui.BaseActivity;
import com.ryanair.cheapflights.ui.Extras;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import dagger.ObjectGraph;

public class FlightInfoResultsActivity extends BaseActivity implements FlightInfoResultsView {
    @Inject
    FlightInfoResultsPresenter presenter;

    @InjectView(R.id.flight_info_results_list)
    RecyclerView resultsList;

    private FlightInfoResultsAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_flight_info_results;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectGraph objectGraph = ObjectGraph.create(new FlightInfoResultsModule(this));
        objectGraph.inject(this);

        presenter.startSearch();

        resultsList.setLayoutManager(new LinearLayoutManager(this));
        resultsList.setItemAnimator(new DefaultItemAnimator());

        adapter = new FlightInfoResultsAdapter(new FlightInfoResultsAdapter.OnSelectedListener() {
            @Override
            public void onFlightSelected(FlightInfo flightInfo) {
                presenter.selectFlight(flightInfo);

            }
        });
        resultsList.setAdapter(adapter);
    }

    @Override
    public String getFlightNumber() {
        return getIntent().getStringExtra(Extras.FLIGHT_NUMBER);
    }

    @Override
    public void displayFlightInfo(List<FlightInfo> flightInfo) {
        adapter.setFlightInfoResults(flightInfo);
    }

    @Override
    public void displayError(Throwable error) {
        Toast.makeText(this, "Oops! Something unholy just happened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displaySelectedFlight(FlightInfo flightInfo) {
        Toast.makeText(getBaseContext(), flightInfo.getFlightNumber(), Toast.LENGTH_SHORT).show();
    }

}

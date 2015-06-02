package com.ryanair.cheapflights.ui.flightinformation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.ryanair.cheapflights.R;
import com.ryanair.cheapflights.di.FlightInfoSearchModule;
import com.ryanair.cheapflights.presentation.presenter.FlightInfoSearchPresenter;
import com.ryanair.cheapflights.presentation.view.FlightInfoSearchView;
import com.ryanair.cheapflights.ui.BaseActivity;
import com.ryanair.cheapflights.ui.Extras;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import dagger.ObjectGraph;

public class FlightInfoSearchActivity extends BaseActivity implements FlightInfoSearchView {
    @Inject
    FlightInfoSearchPresenter presenter;

    @InjectView(R.id.flight_info_search_edit_flight)
    EditText flightEdit;

    @Override
    protected int getContentView() {
        return R.layout.activity_flight_info_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectGraph objectGraph = ObjectGraph.create(new FlightInfoSearchModule(this));
        objectGraph.inject(this);
    }

    @OnClick(R.id.flight_info_search_button_search)
    void onSearchClick() {
        presenter.search();
    }

    @Override
    public void goToSearchResults(String flightNumber) {
        Intent searchResultsIntent = new Intent(this, FlightInfoResultsActivity.class);
        searchResultsIntent.putExtra(Extras.FLIGHT_NUMBER, flightNumber);
        startActivity(searchResultsIntent);
    }

    @Override
    public String getFlightNumber() {
        return flightEdit.getText().toString();
    }
}

package com.ryanair.cheapflights.ui.flightinformation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryanair.cheapflights.R;
import com.ryanair.cheapflights.entity.FlightInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FlightInfoResultsAdapter extends RecyclerView.Adapter<FlightInfoResultsAdapter.ViewHolder> {
    private final OnSelectedListener onSelectedListener;
    private final List<FlightInfo> flightInfoResults;

    public FlightInfoResultsAdapter(OnSelectedListener listener) {
        this.flightInfoResults = new ArrayList<>();
        this.onSelectedListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_flight_info, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final FlightInfo flightInfo = flightInfoResults.get(i);

        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedListener.onFlightSelected(flightInfo);
            }
        });
        viewHolder.number.setText(flightInfo.getFlightNumber());
    }

    @Override
    public int getItemCount() {
        return flightInfoResults.size();
    }

    public void setFlightInfoResults(List<FlightInfo> results) {
        flightInfoResults.addAll(results);
        notifyDataSetChanged();
    }

    public interface OnSelectedListener {
        void onFlightSelected(FlightInfo flightInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_flight_info_root)
        View root;

        @InjectView(R.id.item_flight_info_number)
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}

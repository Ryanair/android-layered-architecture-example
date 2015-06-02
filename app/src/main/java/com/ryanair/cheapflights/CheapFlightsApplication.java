package com.ryanair.cheapflights;

import android.app.Application;
import android.content.Context;

public class CheapFlightsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static CheapFlightsApplication get(Context context) {
        return (CheapFlightsApplication) context.getApplicationContext();
    }
}
package com.ryanair.cheapflights.data.api.dotRez;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class Api {
    public static <T> T create(Class<T> apiType) {
        Gson gson = new GsonBuilder()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("...")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(apiType);
    }
}

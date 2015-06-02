package com.ryanair.cheapflights.util;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public final class ObservableUtil {
    private ObservableUtil() {
    }

    public static <T> Observable<List<T>> fromCollection(List<T> collection) {
        List<List<T>> collectionForRx = new ArrayList<>();
        collectionForRx.add(collection);
        return Observable.from(collectionForRx);
    }
}

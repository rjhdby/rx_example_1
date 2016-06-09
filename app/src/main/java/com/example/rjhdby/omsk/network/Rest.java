package com.example.rjhdby.omsk.network;

import com.example.rjhdby.omsk.network.model.Offer;
import com.example.rjhdby.omsk.network.model.Status;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

public interface Rest {
    String       URL = "http://85.143.221.39:8080/EatgidServer/";
    Rest api = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(Rest.class);

    @GET("orderStatus/{id}")
    Observable<Status> getOrderStatus(@Path("id") int id);
    @GET("activeSimpleOrder/{id}")
    Observable<List<Offer>> getActiveSimpleOrder(@Path("id") int id);
}

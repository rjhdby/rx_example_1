package com.example.rjhdby.omsk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rjhdby.omsk.network.Rest;
import com.example.rjhdby.omsk.network.model.Offer;

import java.util.HashMap;

import rx.Observable;
import rx.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PublishSubject<Boolean> subject = PublishSubject.create();
        HashMap<Integer, Offer> offers = new HashMap<>();
        Rest.api.getActiveSimpleOrder(1)
                .map(result -> {
                    subject.skip(result.size() - 1).subscribe(b -> Log.d("REQUESTS","COMPLETE"));
                    return result;
                })
                .flatMap(Observable::from)
                .subscribe(offer-> {
                    final Integer id=  offer.id;
                    offers.put(id, offer);
                    Rest.api.getOrderStatus(id)
                            .subscribe(status -> {offers.get(id).setStatus(id);subject.onNext(true);});
                });
    }
}

package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by user on 2016/7/19.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Order.class);
        ParseObject.registerSubclass(Drink.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("x0rKRz14Pv6gi3n9YyCTo81yrw6vIxJCFuF0aTEv")
                .server("https://parseapi.back4app.com/")
                .clientKey("2d998U5ExKHM464P9afTUv968PYQem7wUQ2CtTBQ")
                .enableLocalDataStore()
                .build());
    }
}
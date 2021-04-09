package com.example.letter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("W6qbR8X5QlCgOav8PnplsSb9Ya8psioVl5hrSHi0")
                .clientKey("Qc74XCntTBM64ckt5PGIKVdDxIvDSeHrDAYM3kBs")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

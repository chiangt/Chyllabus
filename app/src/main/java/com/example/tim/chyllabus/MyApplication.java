package com.example.tim.chyllabus;

import android.app.Application;

import com.parse.Parse;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
    }
}

package com.alexbarral.movieapp;

import android.app.Application;

import com.alexbarral.movieapp.injection.component.AppComponent;
import com.alexbarral.movieapp.injection.module.AppModule;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDaggerComponent();
    }

    private AppComponent initDaggerComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

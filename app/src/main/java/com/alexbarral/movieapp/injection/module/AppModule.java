package com.alexbarral.movieapp.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Named("applicationContext")
    public Context providesApplication() {
        return application;
    }
}

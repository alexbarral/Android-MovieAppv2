package com.alexbarral.movieapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.alexbarral.movieapp.data.repository.DataRepository;
import com.alexbarral.movieapp.domain.repository.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    @Singleton
    Context providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Repository provideRepository(DataRepository dataRepository) {
        return dataRepository;
    }

    @Provides
    @Singleton
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}

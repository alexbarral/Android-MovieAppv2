package com.alexbarral.movieapp.injection.component;

import android.content.Context;

import com.alexbarral.movieapp.domain.repository.Repository;
import com.alexbarral.movieapp.injection.module.AppModule;
import com.alexbarral.movieapp.presentation.view.base.BaseActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    Context context();

    @Named("executor_thread")
    Scheduler executorThread();

    @Named("ui_thread")
    Scheduler uiScheduler();

    Repository repository();
}
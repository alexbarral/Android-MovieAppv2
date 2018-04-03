package com.alexbarral.movieapp.injection.component;

import com.alexbarral.movieapp.injection.module.AppModule;
import com.alexbarral.movieapp.presentation.detail.DetailActivity;
import com.alexbarral.movieapp.presentation.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(HomeActivity homeActivity);

    void inject(DetailActivity detailActivity);
}




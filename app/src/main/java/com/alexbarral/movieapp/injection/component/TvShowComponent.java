package com.alexbarral.movieapp.injection.component;

import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.injection.module.ActivityModule;
import com.alexbarral.movieapp.injection.module.TvShowModule;
import com.alexbarral.movieapp.presentation.view.detail.DetailActivity;
import com.alexbarral.movieapp.presentation.view.home.HomeActivity;
import com.alexbarral.movieapp.presentation.view.home.HomeFragment;

import dagger.Component;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, TvShowModule.class})
public interface TvShowComponent extends ActivityComponent {

    void inject(HomeFragment homeFragment);
}

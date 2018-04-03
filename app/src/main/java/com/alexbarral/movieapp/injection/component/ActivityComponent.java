package com.alexbarral.movieapp.injection.component;

import android.app.Activity;

import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
interface ActivityComponent {

    Activity activity();
}

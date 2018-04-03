package com.alexbarral.movieapp.injection.module;

import android.app.Activity;

import com.alexbarral.movieapp.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}

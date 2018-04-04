package com.alexbarral.movieapp.presentation.navigation;

/**
 * Created by alejandrobarral on 3/4/18.
 */

import android.content.Context;
import android.content.Intent;

import com.alexbarral.movieapp.presentation.view.detail.DetailActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToTvShowDetail(Context context, long idTvShow) {
        if (context != null) {
            Intent intentToLaunch = DetailActivity.getCallingIntent(context, idTvShow);
            context.startActivity(intentToLaunch);
        }
    }
}

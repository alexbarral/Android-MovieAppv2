package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.injection.PerActivity;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class HomePresenter implements Presenter {

    @Inject
    public HomePresenter() {
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}

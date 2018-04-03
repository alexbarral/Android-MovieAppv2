package com.alexbarral.movieapp.presentation.view.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.HasComponent;
import com.alexbarral.movieapp.injection.component.DaggerTvShowComponent;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.presenter.DetailPresenter;
import com.alexbarral.movieapp.presentation.view.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class DetailActivity extends BaseActivity implements HasComponent<TvShowComponent>{

    @Inject
    DetailPresenter detailPresenter;

    private TvShowComponent tvShowComponent;

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, DetailActivity.class);
        return callingIntent;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initDagger();
    }

    private void initDagger() {
        this.tvShowComponent = DaggerTvShowComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();

    }


    @Override
    public TvShowComponent getcomponent() {
        return tvShowComponent;
    }
}

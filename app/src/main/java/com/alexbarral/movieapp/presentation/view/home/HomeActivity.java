package com.alexbarral.movieapp.presentation.view.home;

import android.os.Bundle;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.HasComponent;
import com.alexbarral.movieapp.injection.component.DaggerTvShowComponent;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.view.base.BaseActivity;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class HomeActivity extends BaseActivity implements HasComponent<TvShowComponent> {

    private TvShowComponent tvShowComponent;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initDagger();

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new HomeFragment());
        }
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

package com.alexbarral.movieapp.presentation.view.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.presenter.HomePresenter;
import com.alexbarral.movieapp.presentation.view.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class HomeFragment extends BaseFragment {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.rv_tvshows)
    RecyclerView rv_tvshows;

    @Override
    protected void injectComponent() {
        this.getComponent(TvShowComponent.class).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
    }

    public HomeFragment() {
        setRetainInstance(true);
    }
}

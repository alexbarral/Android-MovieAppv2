package com.alexbarral.movieapp.presentation.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.presenter.HomePresenter;
import com.alexbarral.movieapp.presentation.view.HomeView;
import com.alexbarral.movieapp.presentation.view.base.BaseFragment;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class HomeFragment extends BaseFragment implements HomeView {

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.rv_tvshows)
    RecyclerView rv_tvshows;

    HomeAdapter adapter;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.homePresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    private void loadUserList() {
        homePresenter.initialize();
    }

    private void setupRecyclerView() {
        if(adapter==null) {
            adapter = new HomeAdapter(homePresenter);
            rv_tvshows.setAdapter(adapter);
        }
        rv_tvshows.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tvshows.setHasFixedSize(true);
    }

    public HomeFragment() {
        setRetainInstance(true);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void renderTvShows(Collection<TvShowModel> tvShowModelCollection) {
        adapter.addAll((List<TvShowModel>) tvShowModelCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewTvShow(TvShowModel tvShowModel) {

    }
}

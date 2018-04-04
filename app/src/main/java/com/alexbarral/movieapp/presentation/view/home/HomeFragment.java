package com.alexbarral.movieapp.presentation.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.custom.EndlessScrollListener;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.navigation.Navigator;
import com.alexbarral.movieapp.presentation.presenter.HomePresenter;
import com.alexbarral.movieapp.presentation.presenter.Presenter;
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
    private EndlessScrollListener scrollListener;

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
            adapter = new HomeAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            rv_tvshows.setAdapter(adapter);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_tvshows.setLayoutManager(linearLayoutManager);
        rv_tvshows.setHasFixedSize(true);

        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                homePresenter.onLoadMore();
            }
        };

        rv_tvshows.addOnScrollListener(scrollListener);
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
    public void renderTvShows(ConfigurationModel configuration, Collection<TvShowModel> tvShowModelCollection) {
        adapter.setConfiguration(configuration);
        adapter.addAll((List<TvShowModel>) tvShowModelCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewTvShow(long id) {
        getNavigator().navigateToTvShowDetail(getActivity(), id);
    }

    HomeAdapter.OnItemClickListener onItemClickListener = id -> {
        viewTvShow(id);
    };

}

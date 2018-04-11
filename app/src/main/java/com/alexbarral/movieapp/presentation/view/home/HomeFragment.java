package com.alexbarral.movieapp.presentation.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.custom.EndlessScrollListener;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
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

    @BindView(R.id.progressBar)
    ProgressBar pb_loading;

    @BindView(R.id.rv_tvshows)
    RecyclerView rv_tvshows;

    @BindView(R.id.searchView)
    SearchView searchView;

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
        setupSearchView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.homePresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadTvShowList();
        }
    }

    private void loadTvShowList() {
        homePresenter.initialize();
    }


    private void setupSearchView() {
        searchView.setQueryHint(getString(R.string.search_movie));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchQuery(query);
                return false;
            }
        });
    }

    private void searchQuery(String query) {
        if (query.length() > 3) {
            homePresenter.onSearchQuerie(query);
        }
    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new HomeAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            rv_tvshows.setAdapter(adapter);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_tvshows.setLayoutManager(linearLayoutManager);
        rv_tvshows.setHasFixedSize(false);

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
        pb_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb_loading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showMessage(message);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void renderMovies(Collection<MovieModel> movieModelCollection) {
        adapter.addAll((List<MovieModel>) movieModelCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewMovie(long id) {
        getNavigator().navigateToTvShowDetail(getActivity(), id);
    }

    @Override
    public void setConfiguration(ConfigurationModel configuration) {
        adapter.setConfiguration(configuration);
        adapter.notifyDataSetChanged();
    }

    HomeAdapter.OnItemClickListener onItemClickListener = id -> {
        viewMovie(id);
    };

}

package com.alexbarral.movieapp.presentation.view.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.custom.EndlessScrollListener;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.presenter.DetailPresenter;
import com.alexbarral.movieapp.presentation.util.ConfigurationModelUtil;
import com.alexbarral.movieapp.presentation.view.DetailView;
import com.alexbarral.movieapp.presentation.view.base.BaseFragment;
import com.alexbarral.movieapp.presentation.view.home.HomeAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class DetailFragment extends BaseFragment implements DetailView {

    private static final String PARAM_TVSHOW_ID = "param.tvshow_id";

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.label_title)
    TextView tv_title;
    @BindView(R.id.label_description)
    TextView tv_description;
    @BindView(R.id.backgroundImageView)
    ImageView iv_background;
    @BindView(R.id.progressBar)
    ProgressBar pb_loading;

    @BindView(R.id.rv_similar_tvshows)
    RecyclerView rv_similar_tvshows;


    private DetailAdapter adapter;
    private EndlessScrollListener scrollListener;

    public static DetailFragment forTvShow(long tvShowID) {
        final DetailFragment detailFragment = new DetailFragment();
        final Bundle arguments = new Bundle();
        arguments.putLong(PARAM_TVSHOW_ID, tvShowID);
        detailFragment.setArguments(arguments);
        return detailFragment;
    }

    public DetailFragment() {
        setRetainInstance(true);
    }

    private long currentTvShowId() {
        final Bundle arguments = getArguments();
        return arguments.getLong(PARAM_TVSHOW_ID);
    }

    @Override
    protected void injectComponent() {
        this.getComponent(TvShowComponent.class).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if(adapter==null) {
            adapter = new DetailAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            rv_similar_tvshows.setAdapter(adapter);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rv_similar_tvshows.setLayoutManager(linearLayoutManager);
        rv_similar_tvshows.setHasFixedSize(true);

        scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                detailPresenter.onLoadMore();
            }
        };

        rv_similar_tvshows.addOnScrollListener(scrollListener);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.detailPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadTvShow();
        }
    }

    private void loadTvShow() {
        this.detailPresenter.initialize(currentTvShowId());
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
        return getActivity();
    }

    @Override
    public void viewMovie(ConfigurationModel configuration, MovieModel movie) {

        tv_title.setText(movie.getName());
        tv_description.setText(movie.getOverview());

        String pictureUrl = ConfigurationModelUtil.getPosterUrl(configuration, movie);
        if (!pictureUrl.isEmpty()) {
            iv_background.setVisibility(View.VISIBLE);
            Glide.with(context())
                    .load(pictureUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iv_background);
        } else {
            iv_background.setVisibility(View.GONE);
        }
    }

    @Override
    public void renderSimilarMovies(ConfigurationModel configurationModel, Collection<MovieModel> movieModels) {
        adapter.setConfiguration(configurationModel);
        adapter.addAll((List<MovieModel>) movieModels);
        adapter.notifyDataSetChanged();
    }

    DetailAdapter.OnItemClickListener onItemClickListener = id -> {
        getNavigator().navigateToTvShowDetail(getActivity(), id);
    };
}

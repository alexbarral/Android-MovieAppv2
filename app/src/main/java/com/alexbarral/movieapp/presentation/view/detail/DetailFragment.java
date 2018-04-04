package com.alexbarral.movieapp.presentation.view.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.TvShowComponent;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.presenter.DetailPresenter;
import com.alexbarral.movieapp.presentation.util.ConfigurationModelUtil;
import com.alexbarral.movieapp.presentation.view.DetailView;
import com.alexbarral.movieapp.presentation.view.base.BaseFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

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
    public void viewTvShow(ConfigurationModel configuration, TvShowModel tvShow) {

        tv_title.setText(tvShow.getName());
        tv_description.setText(tvShow.getOverview());

        String pictureUrl = ConfigurationModelUtil.getPosterUrl(configuration, tvShow);
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
}

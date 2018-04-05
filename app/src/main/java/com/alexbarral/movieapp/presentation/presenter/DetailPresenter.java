package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetSimilarTvShows;
import com.alexbarral.movieapp.domain.interactor.GetTvShow;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationToConfigurationModelMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
import com.alexbarral.movieapp.presentation.view.DetailView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class DetailPresenter implements Presenter {

    private final GetTvShow getTvShowUseCase;
    private final GetConfiguration getConfiguration;
    private final GetSimilarTvShows getSimilarTvShows;
    private final TvShowModelToTvShowMapper tvshowMapper;
    private final ConfigurationToConfigurationModelMapper configurationMapper;
    private DetailView detailView;
    private ConfigurationModel configurationModel;
    private long tvShowId;
    private int page = 1;


    @Inject
    public DetailPresenter(GetConfiguration getConfiguration, GetTvShow getTvShowUseCase, GetSimilarTvShows getSimilarTvShows,
                           ConfigurationToConfigurationModelMapper configurationMapper, TvShowModelToTvShowMapper tvshowMapper) {
        this.getConfiguration = getConfiguration;
        this.getTvShowUseCase = getTvShowUseCase;
        this.getSimilarTvShows = getSimilarTvShows;
        this.configurationMapper = configurationMapper;
        this.tvshowMapper = tvshowMapper;

    }

    public void setView(DetailView detailView) {
        this.detailView = detailView;
    }


    public void initialize(long tvShowId) {
        this.tvShowId = tvShowId;
        this.getConfiguration.execute(new DetailPresenter.ConfigurationObserver(), null);
    }

    private void loadTvShow(){
        this.showLoading();
        this.getTvShow();
    }

    private void loadSimilarContent() {
        this.getSimilarTvShows.execute(new DetailPresenter.SimilarTvShowsObserver(), GetSimilarTvShows.Params.forSimilarTvShows(tvShowId, page));
    }

    private void showLoading() {
        detailView.showLoading();
    }

    private void hideLoading() {
        detailView.hideLoading();
    }

    private void showError(String message) {
        detailView.showError(message);
    }

    private void getTvShow() {
        this.getTvShowUseCase.execute(new DetailPresenter.TvShowObserver(), GetTvShow.Params.forTvShow(tvShowId));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {
        this.getTvShowUseCase.dispose();
        this.getConfiguration.dispose();
        this.detailView = null;
    }

    private final class TvShowObserver extends DefaultObserver<TvShow> {

        @Override
        public void onNext(TvShow tvShow) {
            DetailPresenter.this.showTvShowInView(tvShow);
        }

        @Override
        public void onError(Throwable e) {
            DetailPresenter.this.hideLoading();
            DetailPresenter.this.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            DetailPresenter.this.hideLoading();
            DetailPresenter.this.loadSimilarContent();
        }
    }

    private final class SimilarTvShowsObserver extends DefaultObserver<TvShows> {

        @Override
        public void onNext(TvShows tvShows) {
            DetailPresenter.this.showSimilarTvShowsInView(tvShows);
        }

        @Override
        public void onError(Throwable e) {
            DetailPresenter.this.showError(e.getMessage());
        }

        @Override
        public void onComplete() {}
    }


    private final class ConfigurationObserver extends DefaultObserver<Configuration> {

        @Override
        public void onNext(Configuration configuration) {
            setConfiguration(configuration);
            DetailPresenter.this.loadTvShow();
        }

        @Override
        public void onError(Throwable e) {
            DetailPresenter.this.hideLoading();
            DetailPresenter.this.showError(e.getMessage());

        }

        @Override
        public void onComplete() {}
    }

    private void setConfiguration(Configuration configuration) {
        this.configurationModel = this.configurationMapper.transform(configuration);
    }

    private void showTvShowInView(TvShow tvShow) {
        this.detailView.viewTvShow(this.configurationModel, this.tvshowMapper.transform(tvShow));
    }


    private void showSimilarTvShowsInView(TvShows tvShows) {
        final Collection<TvShowModel> tvShowModels = this.tvshowMapper.transform(tvShows.getTvshows());
        this.detailView.renderSimilarTvShows(configurationModel, tvShowModels);
    }

    public void onLoadMore(){
        this.page++;
        loadSimilarContent();
    }
}

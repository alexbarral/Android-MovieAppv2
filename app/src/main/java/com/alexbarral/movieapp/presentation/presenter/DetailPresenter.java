package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetTvShow;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationToConfigurationModelMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
import com.alexbarral.movieapp.presentation.view.DetailView;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class DetailPresenter implements Presenter {

    private final GetTvShow getTvShowUseCase;
    private final GetConfiguration getConfiguration;
    private final TvShowModelToTvShowMapper tvshowMapper;
    private final ConfigurationToConfigurationModelMapper configurationMapper;
    private DetailView detailView;
    private ConfigurationModel configurationModel;
    private long tvShowId;


    @Inject
    public DetailPresenter(GetConfiguration getConfiguration, GetTvShow getTvShowUseCase,
                           ConfigurationToConfigurationModelMapper configurationMapper, TvShowModelToTvShowMapper tvshowMapper) {
        this.getConfiguration = getConfiguration;
        this.getTvShowUseCase = getTvShowUseCase;
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
        }
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
        public void onComplete() {
            DetailPresenter.this.hideLoading();
        }
    }

    private void setConfiguration(Configuration configuration) {
        this.configurationModel = this.configurationMapper.transform(configuration);
    }

    private void showTvShowInView(TvShow tvShow) {
        this.detailView.viewTvShow(this.configurationModel, this.tvshowMapper.transform(tvShow));
    }
}

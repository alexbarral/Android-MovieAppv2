package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationModelDataMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelDataMapper;
import com.alexbarral.movieapp.presentation.view.HomeView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class HomePresenter implements Presenter {

    private final GetTvShows getTvShowsUseCase;
    private final GetConfiguration getConfiguration;

    private final ConfigurationModelDataMapper configurationMapper;
    private final TvShowModelDataMapper tvShowmapper;

    private HomeView homeView;
    private int page = 1;

    @Inject
    public HomePresenter(GetConfiguration getConfiguration, GetTvShows getTvShowsUseCase,
                         TvShowModelDataMapper tvShowmapper, ConfigurationModelDataMapper configurationMapper) {
        this.getConfiguration = getConfiguration;
        this.getTvShowsUseCase = getTvShowsUseCase;
        this.tvShowmapper = tvShowmapper;
        this.configurationMapper = configurationMapper;
    }

    public void setView(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getConfiguration.dispose();
        this.getTvShowsUseCase.dispose();
        this.homeView = null;
    }

    /**
     */
    public void initialize() {
        this.getConfiguration.execute(new ConfigurationObserver(),null);
        getTvShows();
    }

    private void getTvShows() {
        this.showLoading();
        this.getTvShowsUseCase.execute(new TvShowsObserver(), getParams());
    }

    private GetTvShows.Params getParams() {
        return GetTvShows.Params.forTvShows(page);
    }

    private void showLoading() {
        homeView.showLoading();
    }

    private void hideLoading() {
        homeView.hideLoading();
    }

    private void showError(String message) {
        homeView.showError(message);
    }

    private final class TvShowsObserver extends DefaultObserver<TvShows> {

        @Override
        public void onNext(TvShows tvShows) {
            HomePresenter.this.showTvShowsInView(tvShows);
        }

        @Override
        public void onError(Throwable e) {
            HomePresenter.this.hideLoading();
            HomePresenter.this.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            HomePresenter.this.hideLoading();
        }
    }


    private final class ConfigurationObserver extends DefaultObserver<Configuration> {

        @Override
        public void onNext(Configuration configuration) {
            setConfiguration(configuration);
        }

        @Override
        public void onError(Throwable e) {
            HomePresenter.this.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

    private void setConfiguration(Configuration configuration) {
        final ConfigurationModel configurationModel = this.configurationMapper.transform(configuration);
        this.homeView.setConfiguration(configurationModel);
    }

    private void showTvShowsInView(TvShows tvShows) {
        final Collection<TvShowModel> tvShowModels = this.tvShowmapper.transform(tvShows.getTvshows());
        this.homeView.renderTvShows(tvShowModels);
    }

    public void onLoadMore(){
        this.page++;

        getTvShows();
    }
}

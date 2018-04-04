package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationToConfigurationModelMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
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

    private final ConfigurationToConfigurationModelMapper configurationMapper;
    private final TvShowModelToTvShowMapper tvShowmapper;

    private HomeView homeView;
    private int page = 1;
    private ConfigurationModel configurationModel;

    @Inject
    public HomePresenter(GetConfiguration getConfiguration, GetTvShows getTvShowsUseCase,
                         TvShowModelToTvShowMapper tvShowmapper, ConfigurationToConfigurationModelMapper configurationMapper) {
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
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.getConfiguration.execute(new ConfigurationObserver(),null);
    }

    /**
     * Loads all users.
     */
    private void loadTvShows() {
        this.showLoading();
        this.getTvShows();
    }

    private void getTvShows() {
        this.getTvShowsUseCase.execute(new TvShowsObserver(), getParams());
    }

    private GetTvShows.Params getParams() {
        return GetTvShows.Params.forTvShow(page);
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
            HomePresenter.this.loadTvShows();
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

    private void setConfiguration(Configuration configuration) {
        HomePresenter.this.configurationModel = this.configurationMapper.transform(configuration);
    }

    private void showTvShowsInView(TvShows tvShows) {
        final Collection<TvShowModel> tvShowModels = this.tvShowmapper.transform(tvShows.getTvshows());
        this.homeView.renderTvShows(configurationModel, tvShowModels);
    }

    public void onLoadMore(){
        this.page++;
        loadTvShows();
    }
}

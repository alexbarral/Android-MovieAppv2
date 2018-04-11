package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.Movies;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetMovies;
import com.alexbarral.movieapp.domain.interactor.GetSearchMovies;
import com.alexbarral.movieapp.domain.interactor.GetSearchTvShows;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationModelDataMapper;
import com.alexbarral.movieapp.presentation.model.mapper.MovieModelDataMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelDataMapper;
import com.alexbarral.movieapp.presentation.view.HomeView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class HomePresenter implements Presenter {

    private final GetMovies getMoviesUseCase;
    private final GetSearchMovies getSearchMoviesUSeCase;
    private final GetConfiguration getConfiguration;

    private final ConfigurationModelDataMapper configurationMapper;
    private final MovieModelDataMapper movieModelDataMapper;

    private HomeView homeView;
    private int page = 1;
    private String query;

    @Inject
    public HomePresenter(GetConfiguration getConfiguration, GetMovies getMoviesUseCase, GetSearchMovies getSearchMoviesUSeCase,
                         MovieModelDataMapper movieModelDataMapper, ConfigurationModelDataMapper configurationMapper) {
        this.getConfiguration = getConfiguration;
        this.getMoviesUseCase = getMoviesUseCase;
        this.getSearchMoviesUSeCase = getSearchMoviesUSeCase;
        this.movieModelDataMapper = movieModelDataMapper;
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
        this.getMoviesUseCase.dispose();
        this.getSearchMoviesUSeCase.dispose();
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
        this.getMoviesUseCase.execute(new MoviesObserver(), getParams());
    }

    public void onSearchQuerie(String query) {
        this.showLoading();
        this.page = 1;
        this.query = query;
        this.getSearchMoviesUSeCase.execute(new MoviesObserver(), getSearchParams());
    }

    private GetMovies.Params getParams() {
        return GetMovies.Params.forMovies(page);
    }

    private GetSearchMovies.Params getSearchParams() {
        return GetSearchMovies.Params.forSearchMovies(query, page);
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


    private final class MoviesObserver extends DefaultObserver<Movies> {

        @Override
        public void onNext(Movies movies) {
            HomePresenter.this.showMoviesInView(movies);
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

    private void showMoviesInView(Movies movies) {
        final Collection<MovieModel> movieModels = this.movieModelDataMapper.transform(movies.getMovies());
        this.homeView.renderMovies(movieModels);
    }

    public void onLoadMore(){
        this.page++;

        getTvShows();
    }
}

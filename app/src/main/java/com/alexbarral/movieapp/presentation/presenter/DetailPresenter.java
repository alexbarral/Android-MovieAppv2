package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.Movies;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetMovie;
import com.alexbarral.movieapp.domain.interactor.GetSimilarMovies;
import com.alexbarral.movieapp.domain.interactor.GetSimilarTvShows;
import com.alexbarral.movieapp.domain.interactor.GetTvShow;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationModelDataMapper;
import com.alexbarral.movieapp.presentation.model.mapper.MovieModelDataMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelDataMapper;
import com.alexbarral.movieapp.presentation.view.DetailView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class DetailPresenter implements Presenter {

    private final GetMovie getMovieUseCase;
    private final GetConfiguration getConfiguration;
    private final GetSimilarMovies getSimilarMoviesUSeCase;
    private final MovieModelDataMapper movieMapper;
    private final ConfigurationModelDataMapper configurationMapper;
    private DetailView detailView;
    private ConfigurationModel configurationModel;
    private long movieId;
    private int page = 1;


    @Inject
    public DetailPresenter(GetConfiguration getConfiguration, GetMovie getMovieUseCase, GetSimilarMovies getSimilarMoviesUSeCase,
                           ConfigurationModelDataMapper configurationMapper, MovieModelDataMapper movieMapper) {
        this.getConfiguration = getConfiguration;
        this.getMovieUseCase = getMovieUseCase;
        this.getSimilarMoviesUSeCase = getSimilarMoviesUSeCase;
        this.configurationMapper = configurationMapper;
        this.movieMapper = movieMapper;

    }

    public void setView(DetailView detailView) {
        this.detailView = detailView;
    }


    public void initialize(long movieId) {
        this.movieId = movieId;

        this.showLoading();
        this.getConfiguration.execute(new DetailPresenter.ConfigurationObserver(), null);
    }

    private void loadTvShow(){
        this.getTvShow();
    }

    private void loadSimilarContent() {
        this.getSimilarMoviesUSeCase.execute(new DetailPresenter.SimilarMoviesObserver(), GetSimilarMovies.Params.forSimilarMovies(movieId, page));
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
        this.getMovieUseCase.execute(new DetailPresenter.MovieObserver(), GetMovie.Params.forMovie(movieId));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {
        this.getMovieUseCase.dispose();
        this.getSimilarMoviesUSeCase.dispose();
        this.getConfiguration.dispose();
        this.detailView = null;
    }

    private final class MovieObserver extends DefaultObserver<Movie> {

        @Override
        public void onNext(Movie movie) {
            DetailPresenter.this.showMovieInView(movie);
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

    private final class SimilarMoviesObserver extends DefaultObserver<Movies> {

        @Override
        public void onNext(Movies movies) {
            DetailPresenter.this.showSimilarMoviesInView(movies);
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

    private void showMovieInView(Movie movie) {
        this.detailView.viewMovie(this.configurationModel, this.movieMapper.transform(movie));
    }


    private void showSimilarMoviesInView(Movies movies) {
        final Collection<MovieModel> movieModels = this.movieMapper.transform(movies.getMovies());
        this.detailView.renderSimilarMovies(configurationModel, movieModels);
    }

    public void onLoadMore(){
        this.page++;
        loadSimilarContent();
    }
}

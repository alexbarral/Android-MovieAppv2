package com.alexbarral.movieapp.data.net;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.MovieEntity;
import com.alexbarral.movieapp.data.entity.MoviesEntity;
import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class RestApiImpl implements RestApi {

    private final ApiClient apiClient;


    public RestApiImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    @Override
    public Observable<ConfigurationEntity> getConfiguration() {
        return this.apiClient.getRestAPI().getConfiguration();
    }

    @Override
    public Observable<MovieEntity> getMovie(long id) {
        return this.apiClient.getRestAPI().getMovie(id);
    }

    @Override
    public Observable<MoviesEntity> getMovies(int page) {
        return this.apiClient.getRestAPI().getMovies(page);
    }

    @Override
    public Observable<MoviesEntity> getSimilarMovies(long id, int page) {
        return this.apiClient.getRestAPI().getSimilarMovies(id, page);
    }

    @Override
    public Observable<MoviesEntity> getSearchMovies(String query, int page) {
        return this.apiClient.getRestAPI().getSearchMovies(query, page);
    }

    @Override
    public Observable<TvShowEntity> getTvShow(long id) {
        return this.apiClient.getRestAPI().getTvShow(id);
    }

    @Override
    public Observable<TvShowsEntity> getTvShows(int page) {
        return this.apiClient.getRestAPI().getTvShows(page);
    }

    @Override
    public Observable<TvShowsEntity> getSimilarTvShows(long id, int page) {
        return this.apiClient.getRestAPI().getSimilarTvShows(id, page);
    }

    @Override
    public Observable<TvShowsEntity> getSearchTVShows(String query, int page) {
        return this.apiClient.getRestAPI().getSearchTVShows(query, page);
    }
}

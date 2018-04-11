package com.alexbarral.movieapp.data.repository.datasource;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.MovieEntity;
import com.alexbarral.movieapp.data.entity.MoviesEntity;
import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public interface DataStore {

    Observable<ConfigurationEntity> configuration();


    Observable<MovieEntity> movie(long id);

    Observable<MoviesEntity> moviesList(int page);

    Observable<MoviesEntity> similarMovies(long id, int page);

    Observable<MoviesEntity> searchMovies(String query, int page);


    Observable<TvShowEntity> tvShow(long id);

    Observable<TvShowsEntity> tvShowsList(int page);

    Observable<TvShowsEntity> similarTvShows(long id, int page);

    Observable<TvShowsEntity> searchTvShows(String query, int page);
}

package com.alexbarral.movieapp.domain.repository;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.Movies;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.TvShows;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface Repository {


    /**
     * Get an {@link Observable} which will emit a TVshows ITem}.
     */
    Observable<Configuration> configuration();


    /**
     * Get an {@link Observable} which will emit a Movie}.
     */
    Observable<Movie> movie(long id);

    /**
     * Get an {@link Observable} which will emit a Movies List}.
     */
    Observable<Movies> moviesList(int page);

    /**
     * Get an {@link Observable} which will emit a Movies from similar id}.
     */
    Observable<Movies> similarMovies(long id, int page);

    /**
     * Get an {@link Observable} which will emit a Movies list from query}.
     */
    Observable<Movies> searchMovies(String query, int page);


    /**
     * Get an {@link Observable} which will emit a TVshow}.
     */
    Observable<TvShow> tvshow(long id);

    /**
     * Get an {@link Observable} which will emit a TVshows List}.
     */
    Observable<TvShows> tvshowsList(int page);

    /**
     * Get an {@link Observable} which will emit a TVshow from similar id}.
     */
    Observable<TvShows> similarTvshows(long id, int page);

    /**
     * Get an {@link Observable} which will emit a TVshows list from query}.
     */
    Observable<TvShows> searchTvshows(String query, int page);


}

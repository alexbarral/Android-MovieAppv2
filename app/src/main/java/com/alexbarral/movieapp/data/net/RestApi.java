package com.alexbarral.movieapp.data.net;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.MovieEntity;
import com.alexbarral.movieapp.data.entity.MoviesEntity;
import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface RestApi {

    String API_BASE_URL = "https://api.themoviedb.org/3/";


    @GET("configuration")
    Observable<ConfigurationEntity> getConfiguration();


    @GET("movie/{tv_id}")
    Observable<MovieEntity> getMovie(@Path("tv_id") long id);

    @GET("movie/popular")
    Observable<MoviesEntity> getMovies(@Query("page") int page);

    @GET("movie/{movie_id}/similar")
    Observable<MoviesEntity> getSimilarMovies(@Path("movie_id") long id, @Query("page") int page);

    @GET("search/movie")
    Observable<MoviesEntity> getSearchMovies(@Query("query") String query, @Query("page") int page);


    @GET("tv/{tv_id}")
    Observable<TvShowEntity> getTvShow(@Path("tv_id") long id);

    @GET("tv/popular")
    Observable<TvShowsEntity> getTvShows(@Query("page") int page);

    @GET("tv/{tv_id}/similar")
    Observable<TvShowsEntity> getSimilarTvShows(@Path("tv_id") long id, @Query("page") int page);

    @GET("search/tv")
    Observable<TvShowsEntity> getSearchTVShows(@Query("query") String query, @Query("page") int page);
}

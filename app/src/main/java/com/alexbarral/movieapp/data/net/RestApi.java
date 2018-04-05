package com.alexbarral.movieapp.data.net;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
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

    @GET("tv/popular")
    Observable<TvShowsEntity> getTvShows(@Query("page") int page);


    @GET("configuration")
    Observable<ConfigurationEntity> getConfiguration();

    @GET("tv/{tv_id}")
    Observable<TvShowEntity> getTvShow(@Path("tv_id") long id);

    @GET("tv/{tv_id}/similar")
    Observable<TvShowsEntity> getSimilarTvShows(@Path("tv_id") long id, @Query("page") int page);
}

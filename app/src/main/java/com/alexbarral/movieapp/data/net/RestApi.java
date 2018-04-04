package com.alexbarral.movieapp.data.net;

import com.alexbarral.movieapp.data.entity.TvShowsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface RestApi {

    String API_BASE_URL = "https://api.themoviedb.org/3/";

    @GET("tv/popular")
    Observable<TvShowsEntity> getTvShows(@Query("page") int page);

}

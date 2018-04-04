package com.alexbarral.movieapp.data.net;

import com.alexbarral.movieapp.data.entity.TvShowsEntity;
import com.alexbarral.movieapp.domain.TvShows;

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
    public Observable<TvShowsEntity> getTvShows(int page) {
        return this.apiClient.getRestAPI().getTvShows(page);
    }
}

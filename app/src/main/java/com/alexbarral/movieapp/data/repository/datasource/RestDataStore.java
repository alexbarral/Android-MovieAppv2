package com.alexbarral.movieapp.data.repository.datasource;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;
import com.alexbarral.movieapp.data.net.ApiClient;
import com.alexbarral.movieapp.data.net.RestApiImpl;
import com.alexbarral.movieapp.data.net.RestApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class RestDataStore implements DataStore {


    private final RestApi restApi;

    @Inject
    RestDataStore(){
        this.restApi = new RestApiImpl(new ApiClient());
    }


    @Override
    public Observable<TvShowsEntity> tvShowsList(int page) {
        return restApi.getTvShows(page);
    }

    @Override
    public Observable<ConfigurationEntity> configuration() {
        return restApi.getConfiguration();
    }


}

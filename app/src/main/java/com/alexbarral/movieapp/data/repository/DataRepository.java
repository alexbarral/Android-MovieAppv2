package com.alexbarral.movieapp.data.repository;

import com.alexbarral.movieapp.data.entity.mapper.EntityToDataMapper;
import com.alexbarral.movieapp.data.repository.datasource.DataStore;
import com.alexbarral.movieapp.data.repository.datasource.RestDataStore;
import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@Singleton
public class DataRepository implements Repository {


    private final EntityToDataMapper mapper;
    private final DataStore dataStore;

    @Inject
    DataRepository(RestDataStore dataStore, EntityToDataMapper mapper) {
        this.dataStore = dataStore;
        this.mapper = mapper;
    }


    @Override
    public Observable<TvShows> tvshowsList(int page) {
        return dataStore.tvShowsList(page).map(this.mapper::transform);
    }

    @Override
    public Observable<Configuration> configuration() {
        return dataStore.configuration().map(this.mapper::transform);
    }
}

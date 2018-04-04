package com.alexbarral.movieapp.data.entity.mapper;

import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class EntitiyJsonMapper {

    private final Gson gson;

    @Inject
    public EntitiyJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * @param tvShowJsonResponse
     * @return
     * @throws JsonSyntaxException
     */
    public TvShowEntity transformTvShowEntity(String tvShowJsonResponse) throws JsonSyntaxException {
        final Type userEntityType = new TypeToken<TvShowEntity>() {
        }.getType();
        return this.gson.fromJson(tvShowJsonResponse, userEntityType);
    }

    /**
     * @param tvShowsJsonResponse
     * @return
     * @throws JsonSyntaxException
     */
    public TvShowsEntity transformUserEntityCollection(String tvShowsJsonResponse)
            throws JsonSyntaxException {
        final Type tvShowsEntity = new TypeToken<TvShowsEntity>() {
        }.getType();
        return this.gson.fromJson(tvShowsJsonResponse, tvShowsEntity);
    }
}

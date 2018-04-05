package com.alexbarral.movieapp.data.entity.mapper;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;
import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.TvShows;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by alejandrobarral on 4/4/18.
 */

@Singleton
public class EntityToDataMapper {

    @Inject
    EntityToDataMapper() {
    }

    /**
     */
    public TvShow transform(TvShowEntity tvShowEntity) {
        TvShow tvshow = null;
        if (tvShowEntity != null) {
            tvshow = new TvShow();
            tvshow.setId(tvShowEntity.getId());
            tvshow.setName(tvShowEntity.getName());
            tvshow.setBackdrop_path(tvShowEntity.getBackdropPath());
            tvshow.setPoster_path(tvShowEntity.getPosterPath());
            tvshow.setOverview(tvShowEntity.getOverview());
            tvshow.setVote_count(tvShowEntity.getVoteCount());
            tvshow.setVote_average(tvShowEntity.getVoteAverage());
        }
        return tvshow;
    }

    /**
     */
    public TvShows transform(TvShowsEntity tvShowsEntity) {

        TvShows tvShows = null;
        if (tvShowsEntity != null) {
            tvShows = new TvShows();
            tvShows.setPage(tvShowsEntity.getPage());
            tvShows.setTotalpages(tvShowsEntity.getTotalPages());
            tvShows.setTotalresults(tvShowsEntity.getTotalResults());

            tvShows.setTvshows(transform(tvShowsEntity.getResults()));
        }
        return tvShows;
    }

    private List<TvShow> transform(List<TvShowEntity> tvShowEntityList) {

        final List<TvShow> tvShowList = new ArrayList<>(20);
        for (TvShowEntity tvShowEntity : tvShowEntityList) {
            final TvShow tvShow = transform(tvShowEntity);
            if (tvShow != null) {
                tvShowList.add(tvShow);
            }
        }
        return tvShowList;
    }

    public Configuration transform(ConfigurationEntity configurationEntity){
        Configuration configuration = null;
        if (configurationEntity != null) {
            configuration = new Configuration();
            configuration.setBaseUrl(configurationEntity.getImages().getBaseUrl());
            configuration.setPosterSizes(configurationEntity.getImages().getPosterSizes());
        }
        return configuration;
    }
}




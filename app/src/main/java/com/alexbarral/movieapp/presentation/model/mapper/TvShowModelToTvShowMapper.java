package com.alexbarral.movieapp.presentation.model.mapper;

import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class TvShowModelToTvShowMapper {

    @Inject
    TvShowModelToTvShowMapper() {
    }

    /**
     * Transform a {@link TvShow} into an {@link TvShowModel}.
     *
     * @param tvShow Object to be transformed.
     * @return {@link TvShowModel}.
     */
    public TvShowModel transform(TvShow tvShow) {
        if (tvShow == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final TvShowModel tvShowModel = new TvShowModel();
        tvShowModel.setBackdrop_path(tvShow.getBackdrop_path());
        tvShowModel.setFirst_air_date(tvShow.getFirst_air_date());
        tvShowModel.setId(tvShow.getId());
        tvShowModel.setName(tvShow.getName());
        tvShowModel.setOriginal_language(tvShow.getOriginal_language());
        tvShowModel.setOriginal_name(tvShow.getOriginal_name());
        tvShowModel.setOverview(tvShow.getOverview());
        tvShowModel.setPopularity(tvShow.getPopularity());
        tvShowModel.setPoster_path(tvShow.getPoster_path());
        tvShowModel.setVote_average(tvShow.getVote_average());
        tvShowModel.setVote_count(tvShow.getVote_count());

        return tvShowModel;
    }

    /**
     * Transform a collection of {@link TvShow} into a Collection of {@link TvShowModel}.
     *
     * @param tvShowCollection to be transformed.
     * @return List of {@link TvShowModel}.
     */
    public List<TvShowModel> transform(List<TvShow> tvShowCollection) {
        List<TvShowModel> tvShowModelCollection;

        if (tvShowCollection != null && !tvShowCollection.isEmpty()) {
            tvShowModelCollection = new ArrayList<>();
            for (TvShow item : tvShowCollection) {
                tvShowModelCollection.add(transform(item));
            }
        } else {
            tvShowModelCollection = Collections.emptyList();
        }

        return tvShowModelCollection;
    }
}


package com.alexbarral.movieapp.presentation.model.mapper;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class ConfigurationToConfigurationModelMapper {

    @Inject
    ConfigurationToConfigurationModelMapper() {
    }

    /**
     * Transform a {@link Configuration} into an {@link ConfigurationModel}.
     *
     * @param configuration Object to be transformed.
     * @return {@link ConfigurationModel}.
     */
    public ConfigurationModel transform(Configuration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ConfigurationModel configurationModel = new ConfigurationModel();
        configurationModel.setBaseUrl(configuration.getBaseUrl());
        configurationModel.setPosterSizes(configuration.getPosterSizes());

        return configurationModel;
    }

}

package com.alexbarral.movieapp.presentation.model.mapper;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class ConfigurationModelDataMapper {

    @Inject
    ConfigurationModelDataMapper() {
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


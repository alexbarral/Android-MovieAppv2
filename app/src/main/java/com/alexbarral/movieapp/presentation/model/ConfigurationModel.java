package com.alexbarral.movieapp.presentation.model;

import java.util.List;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class ConfigurationModel {

    private String baseUrl;
    private List<String> posterSizes = null;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }
}
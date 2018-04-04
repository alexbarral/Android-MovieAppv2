package com.alexbarral.movieapp.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alejandrobarral on 4/4/18.
 */

public class ConfigurationEntity {
    @SerializedName("images")
    @Expose
    private ImagesEntity images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = null;

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}




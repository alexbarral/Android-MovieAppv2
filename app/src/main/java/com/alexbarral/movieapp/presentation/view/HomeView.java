package com.alexbarral.movieapp.presentation.view;

import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.Collection;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface HomeView extends BaseView {

    /**
     * Render a Movieslist in the UI.
     */
    void renderMovies(Collection<MovieModel> movieModelCollection);

    /**
     * Swhows an especific movie
     *
     * @param id of Movie
     */
    void viewMovie(long id);

    /**
     * Sets the configuration
     */
    void setConfiguration(ConfigurationModel configuration);
}


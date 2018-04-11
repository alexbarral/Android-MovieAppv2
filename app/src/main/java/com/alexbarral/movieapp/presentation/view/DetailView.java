package com.alexbarral.movieapp.presentation.view;

import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.Movies;
import com.alexbarral.movieapp.presentation.model.ConfigurationModel;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.Collection;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface DetailView extends BaseView {

    void viewMovie(ConfigurationModel configuration, MovieModel movieModel);

    void renderSimilarMovies(ConfigurationModel configurationModel, Collection<MovieModel> movieModels);
}


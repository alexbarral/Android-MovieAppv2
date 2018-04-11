package com.alexbarral.movieapp.presentation.model.mapper;

import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.presentation.model.MovieModel;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class MovieModelDataMapper {

    @Inject
    MovieModelDataMapper() {
    }

    /**
     * Transform a {@link Movie} into an {@link MovieModel}.
     *
     * @param movie Object to be transformed.
     * @return {@link MovieModel}.
     */
    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final MovieModel movieModel = new MovieModel();
        movieModel.setBackdrop_path(movie.getBackdrop_path());
        movieModel.setRelease_date(movie.getRelease_date());
        movieModel.setId(movie.getId());
        movieModel.setName(movie.getName());
        movieModel.setOverview(movie.getOverview());
        movieModel.setPopularity(movie.getPopularity());
        movieModel.setPoster_path(movie.getPoster_path());
        movieModel.setVote_average(movie.getVote_average());
        movieModel.setVote_count(movie.getVote_count());

        return movieModel;
    }

    /**
     * Transform a collection of {@link Movie} into a Collection of {@link MovieModel}.
     *
     * @param movieCollecion to be transformed.
     * @return List of {@link MovieModel}.
     */
    public List<MovieModel> transform(List<Movie> movieCollecion) {
        List<MovieModel> movieModelCollection;

        if (movieCollecion != null && !movieCollecion.isEmpty()) {
            movieModelCollection = new ArrayList<>();
            for (Movie item : movieCollecion) {
                movieModelCollection.add(transform(item));
            }
        } else {
            movieModelCollection = Collections.emptyList();
        }

        return movieModelCollection;
    }
}


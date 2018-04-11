package com.alexbarral.movieapp.data.entity.mapper;

import com.alexbarral.movieapp.data.entity.ConfigurationEntity;
import com.alexbarral.movieapp.data.entity.MovieEntity;
import com.alexbarral.movieapp.data.entity.MoviesEntity;
import com.alexbarral.movieapp.data.entity.TvShowEntity;
import com.alexbarral.movieapp.data.entity.TvShowsEntity;
import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.Movies;
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

            tvShows.setTvshows(transformTVShowLis(tvShowsEntity.getResults()));
        }
        return tvShows;
    }

    private List<TvShow> transformTVShowLis(List<TvShowEntity> tvShowEntityList) {

        final List<TvShow> tvShowList = new ArrayList<>(20);
        for (TvShowEntity tvShowEntity : tvShowEntityList) {
            final TvShow tvShow = transform(tvShowEntity);
            if (tvShow != null) {
                tvShowList.add(tvShow);
            }
        }
        return tvShowList;
    }


    /**
     */
    public Movie transform(MovieEntity movieEntity) {
        Movie movie = null;
        if (movieEntity != null) {
            movie = new Movie();
            movie.setId(movieEntity.getId());
            movie.setName(movieEntity.getTitle());
            movie.setBackdrop_path(movieEntity.getBackdropPath());
            movie.setPoster_path(movieEntity.getPosterPath());
            movie.setOverview(movieEntity.getOverview());
            movie.setVote_count(movieEntity.getVoteCount());
            movie.setVote_average(movieEntity.getVoteAverage());
            movie.setRelease_date(movieEntity.getReleaseDate());
        }
        return movie;
    }

    /**
     */
    public Movies transform(MoviesEntity moviesEntity) {

        Movies movies = null;
        if (moviesEntity != null) {
            movies = new Movies();
            movies.setPage(moviesEntity.getPage());
            movies.setTotalpages(moviesEntity.getTotalPages());
            movies.setTotalresults(moviesEntity.getTotalResults());

            movies.setMovies(transform(moviesEntity.getResults()));
        }
        return movies;
    }

    private List<Movie> transform(List<MovieEntity> movieEntityList) {

        final List<Movie> movieList = new ArrayList<>(20);
        for (MovieEntity movieEntity : movieEntityList) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
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




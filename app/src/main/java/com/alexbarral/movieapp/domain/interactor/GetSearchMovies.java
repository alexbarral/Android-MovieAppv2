package com.alexbarral.movieapp.domain.interactor;

import com.alexbarral.movieapp.domain.Movies;
import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class GetSearchMovies extends UseCase<Movies, GetSearchMovies.Params> {

    private final Repository repository;

    @Inject
    public GetSearchMovies(@Named("executor_thread") Scheduler executorThread,
                           @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Movies> createObservableUseCase(Params params) {
        return  this.repository.searchMovies(params.query, params.page);
    }

    public static final class Params {

        private final int page;
        private final String query;

        private Params(String query, int page) {
            this.query = query;
            this.page = page;
        }

        public static Params forSearchMovies(String query, int page) {
            return new Params(query, page);
        }
    }
}

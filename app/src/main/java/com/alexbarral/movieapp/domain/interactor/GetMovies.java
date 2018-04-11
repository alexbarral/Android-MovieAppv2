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

public class GetMovies extends UseCase<Movies, GetMovies.Params> {

    private final Repository repository;

    @Inject
    public GetMovies(@Named("executor_thread") Scheduler executorThread,
                     @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Movies> createObservableUseCase(Params params) {
        return  this.repository.moviesList(params.page);
    }

    public static final class Params {

        private final int page;

        private Params(int page) {
            this.page = page;
        }

        public static Params forMovies(int page) {
            return new Params(page);
        }
    }
}

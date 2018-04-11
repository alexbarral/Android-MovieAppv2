package com.alexbarral.movieapp.domain.interactor;

import com.alexbarral.movieapp.domain.Movie;
import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class GetMovie extends UseCase<Movie, GetMovie.Params> {

    private final Repository repository;

    @Inject
    public GetMovie(@Named("executor_thread") Scheduler executorThread,
                    @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Movie> createObservableUseCase(Params params) {
        return  this.repository.movie(params.id);
    }

    public static final class Params {

        private final long id;

        private Params(long id) {
            this.id = id;
        }

        public static Params forMovie(long id) {
            return new Params(id);
        }
    }
}

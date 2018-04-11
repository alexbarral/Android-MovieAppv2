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

public class GetSimilarMovies extends UseCase<Movies, GetSimilarMovies.Params> {

    private final Repository repository;

    @Inject
    public GetSimilarMovies(@Named("executor_thread") Scheduler executorThread,
                            @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Movies> createObservableUseCase(Params params) {
        return  this.repository.similarMovies(params.id, params.page);
    }

    public static final class Params {

        private final long id;
        private final int page;

        private Params(long id, int page) {
            this.id = id;
            this.page = page;
        }

        public static Params forSimilarMovies(long id, int page) {
            return new Params(id, page);
        }
    }
}

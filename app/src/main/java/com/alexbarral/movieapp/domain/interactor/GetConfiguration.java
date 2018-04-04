package com.alexbarral.movieapp.domain.interactor;

import com.alexbarral.movieapp.domain.Configuration;
import com.alexbarral.movieapp.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class GetConfiguration extends UseCase<Configuration, Void> {

    private final Repository repository;

    @Inject
    public GetConfiguration(@Named("executor_thread") Scheduler executorThread,
                            @Named("ui_thread") Scheduler uiThread, Repository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Configuration> createObservableUseCase(Void unused) {
        return  this.repository.configuration();
    }
}

package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.TvShows;
import com.alexbarral.movieapp.domain.interactor.DefaultObserver;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.injection.PerActivity;
import com.alexbarral.movieapp.presentation.model.TvShowModel;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
import com.alexbarral.movieapp.presentation.view.HomeView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alejandrobarral on 3/4/18.
 */

@PerActivity
public class HomePresenter implements Presenter {

    private final GetTvShows getTvShowsUseCase;
    private final TvShowModelToTvShowMapper mapper;
    private HomeView homeView;
    private int page = 1;

    @Inject
    public HomePresenter(GetTvShows getTvShowsUseCase, TvShowModelToTvShowMapper mapper) {
        this.getTvShowsUseCase = getTvShowsUseCase;
        this.mapper = mapper;
    }

    public void setView(HomeView homeView) {
        this.homeView = homeView;

    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getTvShowsUseCase.dispose();
        this.homeView = null;
    }


    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadTvShows();
    }

    /**
     * Loads all users.
     */
    private void loadTvShows() {
        this.showLoading();
        this.getTvShows();
    }

    private void getTvShows() {
        this.getTvShowsUseCase.execute(new TvShowsObserver(), getParams());
    }

    private GetTvShows.Params getParams() {
        return GetTvShows.Params.forTvShow(page);
    }

    private void showLoading() {
        homeView.showLoading();
    }

    private void hideLoading() {
        homeView.hideLoading();
    }

    private void showError(String message) {
        homeView.showError(message);
    }

    private final class TvShowsObserver extends DefaultObserver<TvShows> {

        @Override
        public void onNext(TvShows tvShows) {
            HomePresenter.this.showTvShowsInView(tvShows);
        }

        @Override
        public void onError(Throwable e) {
            HomePresenter.this.hideLoading();
            HomePresenter.this.showError(e.getMessage());

        }

        @Override
        public void onComplete() {
            HomePresenter.this.hideLoading();
        }
    }

    private void showTvShowsInView(TvShows tvShows) {
        final Collection<TvShowModel> tvShowModels = this.mapper.transform(tvShows.getTvshows());
        this.homeView.renderTvShows(tvShowModels);
    }
}

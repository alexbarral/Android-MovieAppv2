package com.alexbarral.movieapp.presentation.view;

import com.alexbarral.movieapp.presentation.model.TvShowModel;

import java.util.Collection;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface HomeView extends BaseView {

    /**
     * Render a tvShowlist in the UI.
     */
    void renderTvShows(Collection<TvShowModel> tvShowModelCollection);

    /**
     * Swhows an especific tvshow
     *
     * @param tvShowModel
     */
    void viewTvShow(TvShowModel tvShowModel);
}


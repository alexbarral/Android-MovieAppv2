package com.alexbarral.movieapp.domain.repository;

import com.alexbarral.movieapp.domain.TvShows;

import io.reactivex.Observable;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public interface Repository {

    /**
     * Get an {@link Observable} which will emit a TVshows ITem}.
     */
    Observable<TvShows> tvshowsList(int page);
}

package com.alexbarral.movieapp.presentation.presenter;

import android.content.Context;
import android.test.mock.MockDialogInterface;

import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetSimilarTvShows;
import com.alexbarral.movieapp.domain.interactor.GetTvShow;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationToConfigurationModelMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
import com.alexbarral.movieapp.presentation.view.DetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by alejandrobarral on 5/4/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    private static final long TVSHOW_ID = 1;

    @Mock private DetailView mockDetailView;
    @Mock private GetConfiguration mockGetConfiguration;
    @Mock private GetTvShow mockGetTvShow;
    @Mock private GetSimilarTvShows mockGetSimilarTvShows;
    @Mock private ConfigurationToConfigurationModelMapper mockConfigurationModelMapper;
    @Mock private TvShowModelToTvShowMapper mockTvShowModelMapper;

    private DetailPresenter detailPresenter;

    @Before
    public void setUp() throws Exception {
        detailPresenter = new DetailPresenter(mockGetConfiguration, mockGetTvShow, mockGetSimilarTvShows,
                mockConfigurationModelMapper, mockTvShowModelMapper);

        detailPresenter.setView(mockDetailView);
    }

    @Test
    public void testPresenterInitialize(){

        detailPresenter.initialize(TVSHOW_ID);

        verify(mockDetailView).showLoading();
        verify(mockGetConfiguration).execute(any(DisposableObserver.class), any());
    }

}
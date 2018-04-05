package com.alexbarral.movieapp.presentation.presenter;

import com.alexbarral.movieapp.domain.interactor.GetConfiguration;
import com.alexbarral.movieapp.domain.interactor.GetTvShow;
import com.alexbarral.movieapp.domain.interactor.GetTvShows;
import com.alexbarral.movieapp.presentation.model.mapper.ConfigurationToConfigurationModelMapper;
import com.alexbarral.movieapp.presentation.model.mapper.TvShowModelToTvShowMapper;
import com.alexbarral.movieapp.presentation.view.HomeView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by alejandrobarral on 5/4/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    private static final long TVSHOW_ID = 1;

    @Mock
    private HomeView mockHomeView;
    @Mock private GetConfiguration mockGetConfiguration;
    @Mock private GetTvShows mockGetTvShows;
    @Mock private ConfigurationToConfigurationModelMapper mockConfigurationModelMapper;
    @Mock private TvShowModelToTvShowMapper mockTvShowModelMapper;

    private HomePresenter homePresenter;

    @Before
    public void setUp() throws Exception {
        homePresenter = new HomePresenter(mockGetConfiguration, mockGetTvShows, mockTvShowModelMapper, mockConfigurationModelMapper);
        homePresenter.setView(mockHomeView);
    }

    @Test
    public void testPresenterInitialize(){

        homePresenter.initialize();

        verify(mockHomeView).showLoading();
        verify(mockGetConfiguration).execute(any(DisposableObserver.class), any());
        verify(mockGetTvShows).execute(any(DisposableObserver.class), any());
    }

    @Test
    public void onLadMore(){

        homePresenter.onLoadMore();

        verify(mockHomeView).showLoading();
        verify(mockGetTvShows).execute(any(DisposableObserver.class), any());
    }

}
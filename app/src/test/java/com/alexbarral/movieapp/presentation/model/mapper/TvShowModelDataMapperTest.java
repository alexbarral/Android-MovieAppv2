package com.alexbarral.movieapp.presentation.model.mapper;

import com.alexbarral.movieapp.domain.TvShow;
import com.alexbarral.movieapp.presentation.model.TvShowModel;

import junit.framework.TestCase;

import org.junit.Before;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by alejandrobarral on 5/4/18.
 */
public class TvShowModelDataMapperTest extends TestCase{

    private static final long FAKE_TVSHOW_ID = 22;
    private static final String FAKE_TVSHOW_NAME = "tvshow_Pepe";

    private TvShowModelDataMapper tvShowModelDataMapper;

    @Before
    public void setUp() throws Exception {
        tvShowModelDataMapper = new TvShowModelDataMapper();
    }

    public void testTransformTvShow() {
        TvShow tvShow = getFakeTvShow();
        TvShowModel tvShowModel = tvShowModelDataMapper.transform(tvShow);

        assertThat(tvShowModel, is(instanceOf(TvShowModel.class)));
        assertThat(tvShowModel.getId(), is(FAKE_TVSHOW_ID));
        assertThat(tvShowModel.getName(), is(FAKE_TVSHOW_NAME));
    }

    public void testTransformUserCollection() {
        TvShow mockTvShowOne = mock(TvShow.class);
        TvShow mockTVShowTwo = mock(TvShow.class);

        List<TvShow> tvShowList = new ArrayList<TvShow>(5);
        tvShowList.add(mockTvShowOne);
        tvShowList.add(mockTVShowTwo);

        Collection<TvShowModel> tvShowModelList = tvShowModelDataMapper.transform(tvShowList);

        assertThat(tvShowModelList.toArray()[0], is(instanceOf(TvShowModel.class)));
        assertThat(tvShowModelList.toArray()[1], is(instanceOf(TvShowModel.class)));
        assertThat(tvShowModelList.size(), is(2));
    }

    private TvShow getFakeTvShow() {
        TvShow tvShow = new TvShow();
        tvShow.setId(FAKE_TVSHOW_ID);
        tvShow.setName(FAKE_TVSHOW_NAME);
        return tvShow;
    }

}
package com.alexbarral.movieapp.domain;

import java.util.List;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class TvShows {

    private long page;
    private long totalresults;
    private long totalpages;

    private List<TvShow> tvshows;


    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(long totalresults) {
        this.totalresults = totalresults;
    }

    public long getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(long totalpages) {
        this.totalpages = totalpages;
    }

    public List<TvShow> getTvshows() {
        return tvshows;
    }

    public void setTvshows(List<TvShow> tvshows) {
        this.tvshows = tvshows;
    }
}



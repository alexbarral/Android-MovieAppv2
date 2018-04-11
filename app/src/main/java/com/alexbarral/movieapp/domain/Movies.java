package com.alexbarral.movieapp.domain;

import java.util.List;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public class Movies {

    private long page;
    private long totalresults;
    private long totalpages;

    private List<Movie> movies;


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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}



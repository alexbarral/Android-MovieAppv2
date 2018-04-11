package com.alexbarral.movieapp.presentation.util;

import com.alexbarral.movieapp.presentation.model.MovieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alejandrobarral on 11/4/18.
 */

public class StringFormatter {


    public String getReleaseYear(MovieModel movie) {
        if (movie.getRelease_date().equals("")) {
            return "...";
        }
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = form.parse(movie.getRelease_date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormatter = new SimpleDateFormat("yyyy");
        return postFormatter.format(date);
    }
}

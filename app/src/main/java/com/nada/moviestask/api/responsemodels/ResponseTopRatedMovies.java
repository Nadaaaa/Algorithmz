package com.nada.moviestask.api.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nada.moviestask.models.Movie;

import java.util.List;

public class ResponseTopRatedMovies {

    @SerializedName("results")
    @Expose
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }
}

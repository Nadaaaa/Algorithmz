package com.nada.moviestask.api.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nada.moviestask.models.Genre;

import java.util.List;

public class ResponseMoviesGenre {

    @SerializedName("genres")
    @Expose
    private List<Genre> genreList;

    public List<Genre> getGenreList() {
        return genreList;
    }
}

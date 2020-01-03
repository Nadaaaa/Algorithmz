package com.nada.moviestask.api.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nada.moviestask.models.Genre;
import com.nada.moviestask.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class ResponseMovieDetails {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

   @SerializedName("vote_average")
    @Expose
    private double vote_average;

    @SerializedName("vote_count")
    @Expose
    private long vote_count;

    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genreList;

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

   public double getVote_average() {
        return vote_average;
    }

    public long getVote_count() {
        return vote_count;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    private Movie movie;


    public Movie getMovie() {
        return movie;
    }

    public String getTitle() {
        return title;
    }
}

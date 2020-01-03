package com.nada.moviestask.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nada.moviestask.utils.Constants;

import java.util.ArrayList;

public class Movie {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String poster;

    private String genre;

    @SerializedName("genres")
    @Expose
    private ArrayList<Genre> genreList;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("vote_count")
    @Expose
    private String voteCount;

    @SerializedName("vote_average")
    @Expose
    private String voteAverage;

    @SerializedName("genre_ids")
    @Expose
    private ArrayList<Integer> genreIds;

    private ArrayList<String> genreNameList;

    @BindingAdapter("poster_image")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(Constants.BASE_IMAGE_URL + imageUrl).apply(new RequestOptions())
                .into(imageView);
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }


    public ArrayList<String> getGenreNameList() {
        return genreNameList;
    }

    public void setGenreNameList(ArrayList<String> genreNameList) {
        this.genreNameList = genreNameList;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenreIds(ArrayList<Integer> genreIds) {
        this.genreIds = genreIds;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

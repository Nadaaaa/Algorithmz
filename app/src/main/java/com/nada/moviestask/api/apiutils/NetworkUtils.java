package com.nada.moviestask.api.apiutils;

import com.nada.moviestask.api.responsemodels.ResponseMovieDetails;
import com.nada.moviestask.api.responsemodels.ResponseMoviesGenre;
import com.nada.moviestask.api.responsemodels.ResponseTopRatedMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkUtils {

    @GET("movie/top_rated")
    Observable<ResponseTopRatedMovies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("genre/movie/list")
    Observable<ResponseMoviesGenre> getMovieGenre(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Observable<ResponseMovieDetails> getMovieDetails(
            @Path("movie_id") long movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

}

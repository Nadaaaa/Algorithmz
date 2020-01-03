package com.nada.moviestask.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.nada.moviestask.api.apiutils.Retrofit;
import com.nada.moviestask.api.responsemodels.ResponseMovieDetails;
import com.nada.moviestask.models.Genre;
import com.nada.moviestask.models.Movie;
import com.nada.moviestask.utils.Constants;

import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MovieDetailsRepository {

    private static final String TAG = MovieDetailsRepository.class.getName();

    private static MovieDetailsRepository instance;
    private Movie movie;
    private MutableLiveData<Movie> movieMutableData = new MutableLiveData<>();
    private Disposable disposable;

    public static MovieDetailsRepository getInstance() {
        if (instance == null)
            instance = new MovieDetailsRepository();
        return instance;
    }

    private void getMovieDetails(int movieId) {
        Retrofit.getService(Constants.BASE_URL)
                .getMovieDetails(movieId, Constants.API_KEY, Constants.ENGLISH_LANGUAGE, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseMovieDetails>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseMovieDetails responseMovieDetails) {
                        movie = new Movie();
                        movie.setTitle(responseMovieDetails.getTitle());
                        movie.setPoster(responseMovieDetails.getPosterPath());
                        movie.setOriginalLanguage(responseMovieDetails.getOriginalLanguage());
                        movie.setReleaseDate(responseMovieDetails.getReleaseDate());
                        movie.setGenreList(responseMovieDetails.getGenreList());
                        movie.setVoteAverage(String.valueOf(responseMovieDetails.getVote_average()));
                        movie.setVoteCount(String.valueOf(responseMovieDetails.getVote_count()));

                        StringBuilder text = new StringBuilder();
                        for (int i = 0; i < responseMovieDetails.getGenreList().size(); i++) {
                            if (i == responseMovieDetails.getGenreList().size() - 1) {
                                text.append(responseMovieDetails.getGenreList().get(i).getName());
                                break;
                            }
                            text.append(responseMovieDetails.getGenreList().get(i).getName()).append(", ");
                        }
                        movie.setGenre(text.toString());
                        movieMutableData.setValue(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: get Top Rated Movies" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setDisposable(Disposable disposible) {
        this.disposable = disposable;
    }


    public MutableLiveData<Movie> getMovie(int movieId) {
        getMovieDetails(movieId);
        //movieMutableData.setValue(movie);
        return movieMutableData;
    }
}

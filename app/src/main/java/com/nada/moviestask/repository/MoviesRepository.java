package com.nada.moviestask.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.nada.moviestask.api.apiutils.Retrofit;
import com.nada.moviestask.api.responsemodels.ResponseMoviesGenre;
import com.nada.moviestask.api.responsemodels.ResponseTopRatedMovies;
import com.nada.moviestask.models.Genre;
import com.nada.moviestask.models.Movie;
import com.nada.moviestask.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MoviesRepository {

    private static final String TAG = MoviesRepository.class.getName();

    private static MoviesRepository instance;
    private List<Movie> movieList = new ArrayList<>();
    private Map<Integer, String> genreMap = new HashMap<>();
    private MutableLiveData<List<Movie>> movieListMutableData = new MutableLiveData<>();
    private Disposable disposable;

    public static MoviesRepository getInstance() {
        if (instance == null)
            instance = new MoviesRepository();
        return instance;
    }

    private void getTopRatedMovies() {
        Retrofit.getService(Constants.BASE_URL)
                .getTopRatedMovies(Constants.API_KEY, Constants.ENGLISH_LANGUAGE, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseTopRatedMovies>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseTopRatedMovies responseTopRatedMovies) {
                        movieList.addAll(responseTopRatedMovies.getResults());
                        movieListMutableData.setValue(setEachMovieGenres(movieList));
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

    private List<Movie> setEachMovieGenres(List<Movie> movieList) {
        for (Movie movie : movieList) {
            ArrayList<String> genreList = new ArrayList<>();
            for (Integer i : movie.getGenreIds()) {
                genreList.add(genreMap.get(i));
            }
            movie.setGenreNameList(genreList);

            StringBuilder text = new StringBuilder();
            for (int i = 0; i < movie.getGenreNameList().size(); i++) {
                if (i == movie.getGenreNameList().size() - 1) {
                    text.append(movie.getGenreNameList().get(i));
                    break;
                }
                text.append(movie.getGenreNameList().get(i)).append(", ");
            }
            movie.setGenre(text.toString());

        }
        return movieList;
    }

    private void getMoviesGenre() {
        Retrofit.getService(Constants.BASE_URL)
                .getMovieGenre(Constants.API_KEY, Constants.ENGLISH_LANGUAGE, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseMoviesGenre>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseMoviesGenre responseMoviesGenre) {
                        for (Genre genre : responseMoviesGenre.getGenreList())
                            genreMap.put(genre.getId(), genre.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: get Movies Genre" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setDisposable(Disposable disposible) {
        this.disposable = disposable;
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        getMoviesGenre();
        getTopRatedMovies();
        movieListMutableData.setValue(movieList);
        return movieListMutableData;
    }
}

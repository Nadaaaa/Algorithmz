package com.nada.moviestask.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nada.moviestask.models.Movie;
import com.nada.moviestask.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;
    @Inject
    MoviesRepository moviesRepository;
    private Disposable disposable;

    public MutableLiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    @Inject
    public void init() {
        if (movieList != null)
            return;
        moviesRepository = MoviesRepository.getInstance();
        moviesRepository.setDisposable(disposable);
        movieList = moviesRepository.getMovieList();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
       // disposable.dispose();
    }
}

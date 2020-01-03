package com.nada.moviestask.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nada.moviestask.models.Movie;
import com.nada.moviestask.repository.MovieDetailsRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MovieDetailsActivityViewModel extends ViewModel {

    private MutableLiveData<Movie> movie;
    @Inject
    MovieDetailsRepository movieDetailsRepository;
    private Disposable disposable;

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }

    @Inject
    public void init(int movieId) {
        if (movie != null)
            return;
        movieDetailsRepository = MovieDetailsRepository.getInstance();
        movieDetailsRepository.setDisposable(disposable);
        movie = movieDetailsRepository.getMovie(movieId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
       // disposable.dispose();
    }
}

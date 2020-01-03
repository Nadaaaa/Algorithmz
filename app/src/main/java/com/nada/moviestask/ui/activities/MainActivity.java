package com.nada.moviestask.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.nada.moviestask.R;
import com.nada.moviestask.adapters.MovieAdapter;
import com.nada.moviestask.databinding.ActivityMainBinding;
import com.nada.moviestask.models.Movie;
import com.nada.moviestask.utils.Constants;
import com.nada.moviestask.viewmodels.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private MovieAdapter movieAdapter;
    @Inject
    MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private Parcelable moviesListState;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movieList) {
                        movieAdapter.notifyDataSetChanged();
                    }
                });
                initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (moviesListState != null) {
            binding.rvMovies.getLayoutManager().onRestoreInstanceState(moviesListState);
        }
    }

    private void initRecyclerView() {
        movieAdapter = new MovieAdapter(this, mainActivityViewModel.getMovieList().getValue(), this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        binding.rvMovies.setLayoutManager(gridLayoutManager);
        binding.rvMovies.setAdapter(movieAdapter);
    }

    @Override
    public void onItemClicked(int itemPosition) {
        startActivity(new Intent(MainActivity.this, MovieDetailsActivity.class)
                .putExtra(Constants.MOVIE_ID, mainActivityViewModel.getMovieList().getValue().get(itemPosition).getId()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.MOVIE_LIST_POSITION, gridLayoutManager.findLastVisibleItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        moviesListState = state.getParcelable(Constants.MOVIE_LIST_POSITION);
        gridLayoutManager.onRestoreInstanceState(moviesListState);
    }
}

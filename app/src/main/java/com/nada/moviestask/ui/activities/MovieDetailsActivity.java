package com.nada.moviestask.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.nada.moviestask.R;
import com.nada.moviestask.databinding.ActivityMovieDetailsBinding;
import com.nada.moviestask.models.Movie;
import com.nada.moviestask.utils.Constants;
import com.nada.moviestask.viewmodels.MovieDetailsActivityViewModel;

import javax.inject.Inject;

public class MovieDetailsActivity extends AppCompatActivity {

    @Inject
    MovieDetailsActivityViewModel movieDetailsActivityViewModel;
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        movieDetailsActivityViewModel = ViewModelProviders.of(this).get(MovieDetailsActivityViewModel.class);
        movieDetailsActivityViewModel.init(getIntent().getIntExtra(Constants.MOVIE_ID, 0));
        movieDetailsActivityViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.setMovie(movie);
                binding.setPosterUrl(movie.getPoster());
            }
        });

        ClickHandlers handlers = new ClickHandlers(getApplicationContext());
        binding.setClickHandlers(handlers);

    }

    public class ClickHandlers {

        Context context;

        ClickHandlers(Context context) {
            this.context = context;
        }

        public void back(View view) {
            MovieDetailsActivity.this.finish();
        }
    }
}

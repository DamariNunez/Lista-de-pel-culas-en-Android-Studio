package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.d
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        d { "onCreate" }

        // crate the adapter
        movieAdapter = MovieAdapter(
            movieSelected = {
                d { "Selected movie $it!!!" }
                selectedMovie(it)
            },
            removeMovie = {
                d { "Remove movie $it !!!" }
                removeMovie(it)
            }
        )

        // set the adapter
        findViewById<RecyclerView>(R.id.movieList).adapter = movieAdapter

        // subscribe to data changes
        lifecycleScope.launchWhenResumed {
            viewModel.movies.collect {
                // submit list
                movieAdapter.submitList(it)
            }
        }
    }

    private fun removeMovie(movie: Movie) {
        viewModel.removeMovie(movie)
    }

    private fun selectedMovie(movie: Movie) {
        d { "Movie selected: $movie" }
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("id", movie.id)
        intent.putExtra("name", movie.name)
        intent.putExtra("release", movie.release)
        intent.putExtra("playtime", movie.playtime)
        intent.putExtra("description", movie.description)
        intent.putExtra("imageUrl", movie.imageUrl)
        startActivity(intent)
    }
}
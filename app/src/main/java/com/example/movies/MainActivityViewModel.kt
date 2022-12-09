package com.example.movies

import androidx.lifecycle.ViewModel

class MainActivityViewModel( val model: Model) :
    ViewModel() {

    val movies = model.movies

    fun removeMovie(movie: Movie) {
        model.removeMovie(movie)
    }
}
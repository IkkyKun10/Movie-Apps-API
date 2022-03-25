package com.riezki.latihan.moviecatalogdb.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()

}
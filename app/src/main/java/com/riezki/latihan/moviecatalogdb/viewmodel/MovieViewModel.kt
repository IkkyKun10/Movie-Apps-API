package com.riezki.latihan.moviecatalogdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()

}
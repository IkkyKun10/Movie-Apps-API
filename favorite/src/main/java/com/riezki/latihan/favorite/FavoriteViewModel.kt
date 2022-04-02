package com.riezki.latihan.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies().asLiveData()
}
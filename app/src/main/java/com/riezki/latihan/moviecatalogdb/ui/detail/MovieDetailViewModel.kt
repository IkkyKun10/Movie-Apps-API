package com.riezki.latihan.moviecatalogdb.ui.detail

import androidx.lifecycle.ViewModel
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movies, state: Boolean) =
        movieUseCase.setFavoriteMovie(movie, state)

}
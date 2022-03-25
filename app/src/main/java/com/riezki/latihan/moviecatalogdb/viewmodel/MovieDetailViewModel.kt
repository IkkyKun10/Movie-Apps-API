package com.riezki.latihan.moviecatalogdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.moviecatalogdb.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

//    fun getDetailMovie(movieId: Int) =
//        movieUseCase.getDetailMovie(movieId).asLiveData()
//
//
//    fun getDetailGenre(movieId: Int) =
//        movieUseCase.getGenreMovieDetail(movieId).asLiveData()
//
//
//    fun getCompany(movieId: Int) =
//        movieUseCase.getCompaniesMovieDetail(movieId).asLiveData()


    fun setFavoriteMovie(movie: Movies, state: Boolean) =
        movieUseCase.setFavoriteMovie(movie, state)

}
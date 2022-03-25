package com.riezki.latihan.core.domain.repository

import com.riezki.latihan.core.data.Resource
import com.riezki.latihan.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllMovies() : Flow<Resource<List<Movies>>>

//    fun getDetailMovie(movieId: Int) : Flow<Resource<DomainDetailMovie>>
//
//    fun getGenreMovieDetail(movieId: Int) : Flow<Resource<List<DomainGenresItem>>>
//
//    fun getCompaniesMovieDetail(movieId: Int) : Flow<Resource<List<DomainProductionCompaniesItem>>>

    fun getFavoriteMovies() : Flow<List<Movies>>

    fun setFavoriteMovie(movies: Movies, state: Boolean)
}
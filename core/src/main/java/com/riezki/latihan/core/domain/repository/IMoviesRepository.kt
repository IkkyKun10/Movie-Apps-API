package com.riezki.latihan.core.domain.repository

import com.riezki.latihan.core.data.Resource
import com.riezki.latihan.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllMovies() : Flow<Resource<List<Movies>>>

    fun getFavoriteMovies() : Flow<List<Movies>>

    fun setFavoriteMovie(movies: Movies, state: Boolean)
}
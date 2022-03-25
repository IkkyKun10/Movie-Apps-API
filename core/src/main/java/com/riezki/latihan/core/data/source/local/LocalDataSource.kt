package com.riezki.latihan.core.data.source.local

import com.riezki.latihan.core.data.source.local.entity.MovieEntity
import com.riezki.latihan.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(moviesEntity: List<MovieEntity>) = movieDao.insertMovies(moviesEntity)

    fun setFavoriteMovies(moviesEntity: MovieEntity, newState: Boolean) {
        moviesEntity.isFavorite = newState
        movieDao.updateFavoriteMovies(moviesEntity)
    }
}
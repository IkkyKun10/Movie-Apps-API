package com.riezki.latihan.core.data.source.local.room

import androidx.room.*
import com.riezki.latihan.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies() : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getFavoriteMovies() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: List<MovieEntity>)

    @Update
    fun updateFavoriteMovies(movieEntity: MovieEntity)
}
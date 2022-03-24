package com.riezki.latihan.moviecatalogdb.core.domain.repository

import com.riezki.latihan.moviecatalogdb.core.data.Resource
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.network.ApiResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.MovieResponse
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainDetailMovie
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainGenresItem
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainProductionCompaniesItem
import com.riezki.latihan.moviecatalogdb.core.domain.model.Movies
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
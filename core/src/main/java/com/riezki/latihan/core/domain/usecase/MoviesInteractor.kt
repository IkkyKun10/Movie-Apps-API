package com.riezki.latihan.core.domain.usecase

import com.riezki.latihan.core.data.Resource
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val repository: IMoviesRepository) : MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> = repository.getAllMovies()

//    override fun getDetailMovie(movieId: Int): Flow<Resource<DomainDetailMovie>> =
//        repository.getDetailMovie(movieId)
//
//    override fun getGenreMovieDetail(movieId: Int): Flow<Resource<List<DomainGenresItem>>> =
//        repository.getGenreMovieDetail(movieId)
//
//    override fun getCompaniesMovieDetail(movieId: Int): Flow<Resource<List<DomainProductionCompaniesItem>>> {
//        return repository.getCompaniesMovieDetail(movieId)
//    }

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return repository.getFavoriteMovies()
    }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        return repository.setFavoriteMovie(movies, state)
    }
}
package com.riezki.latihan.core.data

import com.riezki.latihan.core.data.source.local.LocalDataSource
import com.riezki.latihan.core.data.source.remote.RemoteDataSource
import com.riezki.latihan.core.data.source.remote.network.ApiResponse
import com.riezki.latihan.core.data.source.remote.response.MovieItemsResponse
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.core.domain.repository.IMoviesRepository
import com.riezki.latihan.core.utils.AppExecutors
import com.riezki.latihan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : com.riezki.latihan.core.data.NetworkBoundResource<List<Movies>, List<MovieItemsResponse>>() {
            override fun loadFromDb(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFecth(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItemsResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCreateCall(data: List<MovieItemsResponse>) {
                val moviesList = DataMapper.mapResponseToEntity(data)
                localDataSource.insertMovies(moviesList)
            }

        }.asFlow()

//    override fun getDetailMovie(movieId: Int): Flow<Resource<DomainDetailMovie>> {
//        return object : NetworkOnlyResource<DomainDetailMovie, DetailMovieResponse>() {
//            override suspend fun loadFromNetwork(data: DetailMovieResponse): Flow<DomainDetailMovie> =
//                DataMapper.mapDetailResponseToDomain(data)
//
//            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> {
//                return remoteDataSource.getDetailMovies(movieId)
//            }
//
//        }.asFlow()
//    }
//
//    override fun getGenreMovieDetail(movieId: Int): Flow<Resource<List<DomainGenresItem>>> {
//        return object : NetworkOnlyResource<List<DomainGenresItem>, List<GenresItem>>() {
//            override suspend fun loadFromNetwork(data: List<GenresItem>): Flow<List<DomainGenresItem>> {
//                return DataMapper.mapGenreDetailToDomain(data)
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<GenresItem>>> {
//                return remoteDataSource.getGenreMovies(movieId)
//            }
//
//        }.asFlow()
//    }
//
//    override fun getCompaniesMovieDetail(movieId: Int): Flow<Resource<List<DomainProductionCompaniesItem>>> {
//        return object : NetworkOnlyResource<List<DomainProductionCompaniesItem>, List<ProductionCompaniesItem>>() {
//            override suspend fun loadFromNetwork(data: List<ProductionCompaniesItem>): Flow<List<DomainProductionCompaniesItem>> {
//                return DataMapper.mapCompanyDetailToDomain(data)
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<ProductionCompaniesItem>>> {
//                return remoteDataSource.getCompaniesItem(movieId)
//            }
//
//        }.asFlow()
//    }


    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }
}
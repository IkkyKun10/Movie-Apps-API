package com.riezki.latihan.moviecatalogdb.core.data.source.remote

import android.util.Log
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.network.ApiResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.network.ApiService
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.DetailMovieResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.GenresItem
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.MovieItemsResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.ProductionCompaniesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies() : Flow<ApiResponse<List<MovieItemsResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArrayMovies = response.movieResponses
                if (dataArrayMovies.isNotEmpty()) {
                    emit(ApiResponse.Success(response.movieResponses))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("GetAllMovies", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

//    suspend fun getDetailMovies(idDetail: Int) : Flow<ApiResponse<DetailMovieResponse>> {
//        return flow {
//            try {
//                val response = apiService.getDetailMovie(idDetail)
//                emit(ApiResponse.Success(response))
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("DetailResponse", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//
//    suspend fun getGenreMovies(idDetail: Int) : Flow<ApiResponse<List<GenresItem>>> {
//        return flow {
//            try {
//                val response = apiService.getDetailMovie(idDetail)
//                val dataArrayGenre = response.genres
//                if (dataArrayGenre != null) {
//                    if (dataArrayGenre.isNotEmpty()) {
//                        emit(ApiResponse.Success(response.genres))
//                    } else {
//                        emit(ApiResponse.Empty)
//                    }
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("GetGenreMovies", e.toString())
//            }
//        }
//    }
//
//    suspend fun getCompaniesItem(idDetail: Int) : Flow<ApiResponse<List<ProductionCompaniesItem>>> {
//        return flow {
//            try {
//                val response = apiService.getDetailMovie(idDetail)
//                val dataArrayCompanies = response.productionCompanies
//                if (dataArrayCompanies != null) {
//                    if (dataArrayCompanies.isNotEmpty()) {
//                        emit(ApiResponse.Success(response.productionCompanies))
//                    } else {
//                        emit(ApiResponse.Empty)
//                    }
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("GetCompanies", e.toString())
//            }
//        }
//    }
}
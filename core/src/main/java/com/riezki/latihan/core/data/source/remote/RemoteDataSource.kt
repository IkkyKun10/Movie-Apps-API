package com.riezki.latihan.core.data.source.remote

import android.util.Log
import com.riezki.latihan.core.data.source.remote.network.ApiResponse
import com.riezki.latihan.core.data.source.remote.network.ApiService
import com.riezki.latihan.core.data.source.remote.response.MovieItemsResponse
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

}
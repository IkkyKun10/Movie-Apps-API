package com.riezki.latihan.core.data.source.remote.network

import com.riezki.latihan.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=dad372f43d831221fe54fb6f127a852b")
    suspend fun getListMovie() : MovieResponse

}
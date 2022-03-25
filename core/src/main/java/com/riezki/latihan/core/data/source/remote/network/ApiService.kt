package com.riezki.latihan.core.data.source.remote.network

import com.riezki.latihan.core.data.source.remote.response.DetailMovieResponse
import com.riezki.latihan.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular?api_key=dad372f43d831221fe54fb6f127a852b")
    suspend fun getListMovie() : MovieResponse

    @GET("movie/{movie_id}?api_key=dad372f43d831221fe54fb6f127a852b")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ) : DetailMovieResponse

}
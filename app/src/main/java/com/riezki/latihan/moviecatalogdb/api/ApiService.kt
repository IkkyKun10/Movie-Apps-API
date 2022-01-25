package com.riezki.latihan.moviecatalogdb.api

import com.riezki.latihan.moviecatalogdb.model.MovieResponse
import com.riezki.latihan.moviecatalogdb.model.TvResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=dad372f43d831221fe54fb6f127a852b")
    fun getListMovie() : Call<MovieResponse>

    @GET("tv/popular?api_key=dad372f43d831221fe54fb6f127a852b")
    fun getListTvShow() : Call<TvResponse>
}
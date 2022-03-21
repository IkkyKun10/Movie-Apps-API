package com.riezki.latihan.moviecatalogdb.api

import com.riezki.latihan.moviecatalogdb.model.DetailMovieResponse
import com.riezki.latihan.moviecatalogdb.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular?api_key=dad372f43d831221fe54fb6f127a852b")
    fun getListMovie() : Call<MovieResponse>

//    @GET("tv/popular?api_key=dad372f43d831221fe54fb6f127a852b")
//    fun getListTvShow() : Call<TvResponse>

    @GET("movie/{movie_id}?api_key=dad372f43d831221fe54fb6f127a852b")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ) : Call<DetailMovieResponse>

//    @GET("tv/{tv_id}?api_key=dad372f43d831221fe54fb6f127a852b")
//    fun getDetailTvShow(
//        @Path("tv_id") tvId: Int
//    ) : Call<DetailTvResponse>
}
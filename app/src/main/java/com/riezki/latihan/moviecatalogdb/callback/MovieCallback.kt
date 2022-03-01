package com.riezki.latihan.moviecatalogdb.callback

import com.riezki.latihan.moviecatalogdb.model.MovieItems

interface MovieCallback {
    fun onShareClick(movie: MovieItems)

    fun toDetail(movie: MovieItems)
}
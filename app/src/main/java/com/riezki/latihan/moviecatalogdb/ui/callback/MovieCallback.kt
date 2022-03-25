package com.riezki.latihan.moviecatalogdb.ui.callback

import com.riezki.latihan.core.domain.model.Movies

interface MovieCallback {
    fun onShareClick(movieResponse: Movies)

    fun toDetail(movieResponse: Movies)
}
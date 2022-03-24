package com.riezki.latihan.moviecatalogdb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    var movieId: Int,
    var overview: String,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var realeaseDate: String,
    var voteAverage: Double,
    var isFavorite: Boolean
) : Parcelable

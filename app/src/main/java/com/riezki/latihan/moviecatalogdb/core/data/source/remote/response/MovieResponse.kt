package com.riezki.latihan.moviecatalogdb.core.data.source.remote.response

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieResponse(

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val movieResponses: ArrayList<MovieItemsResponse>

) : Parcelable

@Parcelize
data class MovieItemsResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int
) : Parcelable

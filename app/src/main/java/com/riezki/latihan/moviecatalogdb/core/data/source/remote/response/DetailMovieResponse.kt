package com.riezki.latihan.moviecatalogdb.core.data.source.remote.response

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailMovieResponse(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>?,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("budget")
	val budget: Int,

	@field:SerializedName("overview")
	val overview: String?,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem>?,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("tagline")
	val tagline: String
) : Parcelable


@Parcelize
data class ProductionCompaniesItem(

	@field:SerializedName("name")
	val name: String?
	
) : Parcelable

@Parcelize
data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null

) : Parcelable

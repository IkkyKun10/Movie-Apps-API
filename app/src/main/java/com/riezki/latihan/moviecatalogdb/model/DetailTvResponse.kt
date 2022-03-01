package com.riezki.latihan.moviecatalogdb.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DetailTvResponse(

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("genres")
	val genres: List<GenresItemTv?>? = null,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String?,

	@field:SerializedName("created_by")
	val createdBy: List<CreatedByItem?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItemTv?>?,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("vote_average")
	val rating: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("last_air_date")
	val lastAirDate: String,

	@field:SerializedName("homepage")
	val homepage: String
) : Parcelable

@Parcelize
data class ProductionCompaniesItemTv(

	@field:SerializedName("name")
	val name: String?,

//	@field:SerializedName("id")
//	val id: Int
) : Parcelable

@Parcelize
data class CreatedByItem(

//	@field:SerializedName("gender")
//	val gender: Int,
//
//	@field:SerializedName("credit_id")
//	val creditId: String,

	@field:SerializedName("name")
	val name: String? = null

//	@field:SerializedName("profile_path")
//	val profilePath: String,
//
//	@field:SerializedName("id")
//	val id: Int
) : Parcelable

@Parcelize
data class GenresItemTv(

	@field:SerializedName("name")
	val name: String? = null

) : Parcelable

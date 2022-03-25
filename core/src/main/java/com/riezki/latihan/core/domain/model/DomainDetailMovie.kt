package com.riezki.latihan.core.domain.model

data class DomainDetailMovie (
    val title: String,
    val backdropPath: String,
    val genres: List<DomainGenresItem?>?,
    val id: Int,
    val budget: Int,
    val overview: String?,
    val posterPath: String,
    val productionCompanies: List<DomainProductionCompaniesItem>?,
    val releaseDate: String,
    val tagline: String
)

data class DomainProductionCompaniesItem(
    val name: String?
)

data class DomainGenresItem(
    val name: String? = null
)
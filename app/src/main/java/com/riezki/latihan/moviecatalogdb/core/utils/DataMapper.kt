package com.riezki.latihan.moviecatalogdb.core.utils

import com.riezki.latihan.moviecatalogdb.core.data.source.local.entity.MovieEntity
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.DetailMovieResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.GenresItem
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.MovieItemsResponse
import com.riezki.latihan.moviecatalogdb.core.data.source.remote.response.ProductionCompaniesItem
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainDetailMovie
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainGenresItem
import com.riezki.latihan.moviecatalogdb.core.domain.model.DomainProductionCompaniesItem
import com.riezki.latihan.moviecatalogdb.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    fun mapResponseToEntity(input: List<MovieItemsResponse>) : List<MovieEntity> {
        val movieEntity = ArrayList<MovieEntity>()
        input.map {
            val item = MovieEntity(
                movieId = it.id,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                realeaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            movieEntity.add(item)
        }
        return movieEntity
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movies> {
        return input.map {
            Movies(
                movieId = it.movieId,
                overview = it.overview,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                realeaseDate = it.realeaseDate,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(data: Movies) : MovieEntity =
        MovieEntity(
            movieId = data.movieId,
            overview = data.overview,
            title = data.title,
            posterPath = data.posterPath,
            backdropPath = data.backdropPath,
            realeaseDate = data.realeaseDate,
            voteAverage = data.voteAverage,
            isFavorite = data.isFavorite
        )

    fun mapDetailResponseToDomain(input: DetailMovieResponse) : Flow<DomainDetailMovie> =
        flowOf(
            DomainDetailMovie(
                title = input.title,
                backdropPath = input.backdropPath,
                genres = null,
                id = input.id,
                budget = input.budget,
                overview = input.overview,
                posterPath = input.posterPath,
                productionCompanies = null,
                releaseDate = input.releaseDate,
                tagline = input.tagline
            )
        )

    fun mapGenreDetailToDomain(input: List<GenresItem?>) : Flow<List<DomainGenresItem>> =
        flowOf(
            input.map {
                DomainGenresItem(
                    name = it?.name
                )
            }
        )

    fun mapCompanyDetailToDomain(
        input: List<ProductionCompaniesItem?>
    ) : Flow<List<DomainProductionCompaniesItem>> =
        flowOf(
            input.map {
                DomainProductionCompaniesItem(
                    name = it?.name
                )
            }
        )

}
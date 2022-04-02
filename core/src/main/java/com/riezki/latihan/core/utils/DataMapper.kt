package com.riezki.latihan.core.utils

import com.riezki.latihan.core.data.source.local.entity.MovieEntity
import com.riezki.latihan.core.data.source.remote.response.MovieItemsResponse
import com.riezki.latihan.core.domain.model.Movies

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

}
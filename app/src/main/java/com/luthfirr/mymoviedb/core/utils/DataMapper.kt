package com.luthfirr.mymoviedb.core.utils

import com.luthfirr.mymoviedb.core.data.source.local.entity.MovieEntity
import com.luthfirr.mymoviedb.core.data.source.remote.response.MovieResponse
import com.luthfirr.mymoviedb.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id!!,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                popularity = it.popularity,
                releaseDate = it.releaseDate,
                title = it.title,
                originalTitle = it.originalTitle,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                popularity = it.popularity,
                releaseDate = it.releaseDate,
                title = it.title,
                originalTitle = it.originalTitle,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id!!,
        overview = input.overview,
        backdropPath = input.backdropPath,
        posterPath = input.posterPath,
        popularity = input.popularity,
        releaseDate = input.releaseDate,
        title = input.title,
        originalTitle = input.originalTitle,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )
}
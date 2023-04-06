package com.luthfirr.mymoviedb.core.domain.repository

import com.luthfirr.mymoviedb.core.data.Resource
import com.luthfirr.mymoviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>>

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun searchFavoriteMovie(query: String): Flow<List<Movie>>

    fun setFavoriteMovie(tourism: Movie, state: Boolean)
}
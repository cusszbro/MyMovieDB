package com.luthfirr.mymoviedb.core.data.source.local

import com.luthfirr.mymoviedb.core.data.source.local.entity.MovieEntity
import com.luthfirr.mymoviedb.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getPopularMovie(): Flow<List<MovieEntity>> = movieDao.getPopularMovie()

    fun getTopRatedMovie(): Flow<List<MovieEntity>> = movieDao.getTopRatedMovie()

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun searchFavoriteMovie(query: String): Flow<List<MovieEntity>> =
        movieDao.searchFavoriteMovie(query)

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}
package com.luthfirr.mymoviedb.core.domain.usecase

import com.luthfirr.mymoviedb.core.data.Resource
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovie()

    override fun getTopRatedMovie(): Flow<Resource<List<Movie>>> = movieRepository.getTopRatedMovie()

    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun searchFavoriteMovie(query: String): Flow<List<Movie>> =
        movieRepository.searchFavoriteMovie(query)

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(tourism, state)
}
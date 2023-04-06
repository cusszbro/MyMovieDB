package com.luthfirr.mymoviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luthfirr.mymoviedb.core.data.Resource
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) = movieUseCase.setFavoriteMovie(
        movie, newStatus
    )

//    fun getReviewMovie(movieId: Int?): LiveData<Resource<List<Movie>>> {
//        return movieUseCase.getReviewMovie(movieId).asLiveData()
//    }
}
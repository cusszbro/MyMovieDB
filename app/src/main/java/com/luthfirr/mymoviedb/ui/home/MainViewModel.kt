package com.luthfirr.mymoviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luthfirr.mymoviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val popularMovie = movieUseCase.getPopularMovie().asLiveData()
    val topRatedMovie = movieUseCase.getTopRatedMovie().asLiveData()
    val movie = movieUseCase.getAllMovie().asLiveData()
}
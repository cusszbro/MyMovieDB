package com.luthfirr.mymoviedb.core.data.source.remote.network

import androidx.viewbinding.BuildConfig
import com.luthfirr.mymoviedb.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularList(
        @Query("api_key") apiToken: String = "d1a9f6e32c3720c8d185e6d121e6c8a4"
    ): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedList(
        @Query("api_key") apiToken: String = "d1a9f6e32c3720c8d185e6d121e6c8a4"
    ): ListMovieResponse

    @GET("movie/now_playing")
    suspend fun getList(
        @Query("api_key") apiToken: String = "d1a9f6e32c3720c8d185e6d121e6c8a4"
    ): ListMovieResponse
}
package com.luthfirr.mymoviedb.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.luthfirr.mymoviedb.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY release_date DESC")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY release_date DESC, vote_average DESC")
    fun getPopularMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY vote_count DESC, vote_average DESC")
    fun getTopRatedMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_favorite = 1 AND title LIKE :query")
    fun searchFavoriteMovie(
        query: String
    ): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}
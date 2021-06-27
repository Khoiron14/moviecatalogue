package com.khoiron14.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.COLUMN_FAVORITED} = 1")
    fun getMovieFavorites(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.COLUMN_MOVIE_ID} = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM ${TvShowEntity.TABLE_NAME}")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM ${TvShowEntity.TABLE_NAME} WHERE ${TvShowEntity.COLUMN_FAVORITED} = 1")
    fun getTvShowFavorites(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM ${TvShowEntity.TABLE_NAME} WHERE ${TvShowEntity.COLUMN_TV_SHOW_ID} = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}
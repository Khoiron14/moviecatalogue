package com.khoiron14.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.data.source.local.room.AppDao

class LocalDataSource private constructor(private val appDao: AppDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = appDao.getMovies()
    fun getFavoritedMovies(): DataSource.Factory<Int, MovieEntity> = appDao.getMovieFavorites()
    fun getMovie(id: Int): LiveData<MovieEntity> = appDao.getMovie(id)
    fun insertMovies(movies: List<MovieEntity>) = appDao.insertMovies(movies)
    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        appDao.updateMovie(movie)
    }
    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        appDao.updateMovie(movie)
    }

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = appDao.getTvShows()
    fun getFavoritedTvShows(): DataSource.Factory<Int, TvShowEntity> = appDao.getTvShowFavorites()
    fun getTvShow(id: Int): LiveData<TvShowEntity> = appDao.getTvShow(id)
    fun insertTvShows(tvShows: List<TvShowEntity>) = appDao.insertTvShows(tvShows)
    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        appDao.updateTvShow(tvShow)
    }
    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        appDao.updateTvShow(tvShow)
    }
}
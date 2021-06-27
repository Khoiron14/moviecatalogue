package com.khoiron14.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.vo.Resource

/**
 * Created by khoiron14 on 11/25/2019.
 */
interface AppDataSource {
    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}

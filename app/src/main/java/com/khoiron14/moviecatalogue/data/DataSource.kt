package com.khoiron14.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.vo.Resource

/**
 * Created by khoiron14 on 11/25/2019.
 */
interface AcademyDataSource {
    fun getMovieList(): LiveData<Resource<List<MovieEntity>>>
    fun getTvShowList(): LiveData<Resource<List<TvShowEntity>>>
    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoritedMovies(): LiveData<List<MovieEntity>>
    fun getFavoritedTvShows(): LiveData<List<TvShowEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}

package com.khoiron14.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.vo.Resource

/**
 * Created by khoiron14 on 7/23/2019.
 */
class MovieDetailViewModel(
    private val mRepository: Repository
) : ViewModel() {

    private lateinit var movie: LiveData<Resource<MovieEntity>>

    fun setMovie(movieId: Int) {
        movie = mRepository.getMovie(movieId)
    }

    fun getMovie() = movie

    fun setFavoriteMovie() {
        val resource = movie.value

        if (resource?.data != null) {
            val newState = !resource.data.favorited
            mRepository.setMovieFavorite(resource.data, newState)
        }
    }
}
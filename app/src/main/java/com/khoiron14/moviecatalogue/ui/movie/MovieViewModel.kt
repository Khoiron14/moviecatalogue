package com.khoiron14.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.vo.Resource

/**
 * Created by khoiron14 on 7/23/2019.
 */
class MovieViewModel(private val mRepository: Repository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = mRepository.getMovieList()
}
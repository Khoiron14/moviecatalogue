package com.khoiron14.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getMovies() : LiveData<List<MovieEntity>> = repository.getFavoritedMovies()
}
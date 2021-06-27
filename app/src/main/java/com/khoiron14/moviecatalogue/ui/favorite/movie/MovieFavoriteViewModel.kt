package com.khoiron14.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getMovies() : LiveData<PagedList<MovieEntity>> = repository.getFavoritedMovies()

    fun setFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorited
        repository.setMovieFavorite(movieEntity, newState)
    }
}
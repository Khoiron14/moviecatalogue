package com.khoiron14.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShows() : LiveData<List<TvShowEntity>> = repository.getFavoritedTvShows()
}
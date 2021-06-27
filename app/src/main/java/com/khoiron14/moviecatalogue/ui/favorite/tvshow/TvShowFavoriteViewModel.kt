package com.khoiron14.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShows() : LiveData<PagedList<TvShowEntity>> = repository.getFavoritedTvShows()

    fun setFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.favorited
        repository.setTvShowFavorite(tvShowEntity, newState)
    }
}
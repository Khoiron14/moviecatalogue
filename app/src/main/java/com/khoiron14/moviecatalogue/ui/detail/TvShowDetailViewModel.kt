package com.khoiron14.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.vo.Resource

class TvShowDetailViewModel(
    private val mRepository: Repository
) : ViewModel() {

    private lateinit var tvShow: LiveData<Resource<TvShowEntity>>

    fun setTvShow(tvShowId: Int) {
        tvShow = mRepository.getTvShow(tvShowId)
    }

    fun getTvShow() = tvShow

    fun setFavoriteTvShow() {
        val resource = tvShow.value

        if (resource?.data != null) {
            val newState = !resource.data.favorited
            mRepository.setTvShowFavorite(resource.data, newState)
        }
    }
}
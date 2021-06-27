package com.khoiron14.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.vo.Resource

/**
 * Created by khoiron14 on 7/23/2019.
 */
class TvShowViewModel(private val mRepository: Repository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = mRepository.getTvShowList()
}
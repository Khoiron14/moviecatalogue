package com.khoiron14.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.utils.FakeDataDummy
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

/**
 * Created by khoiron14 on 11/18/2019.
 */
class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private val observer: Observer<TvShow?> = mock()
    private val dummyTvShow: TvShow = FakeDataDummy().generateDummyTvShow()[0]
    private val tvShowId: Int = dummyTvShow.id

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(repository)
        viewModel.tvShowId = tvShowId
    }

    @Test
    fun getMovie() {
        val tvShow: MutableLiveData<TvShow> = MutableLiveData()
        tvShow.value = dummyTvShow

        `when`(repository.getTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}
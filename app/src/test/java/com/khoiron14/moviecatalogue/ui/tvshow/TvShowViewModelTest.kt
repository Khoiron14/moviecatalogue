package com.khoiron14.moviecatalogue.ui.tvshow

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
 * Created by khoiron14 on 11/16/2019.
 */
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private val observer: Observer<List<TvShow>?> = mock()
    private val dummyTvShows: ArrayList<TvShow> = FakeDataDummy().generateDummyTvShow()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShowList() {
//        val tvShows: MutableLiveData<List<TvShow>> = MutableLiveData()
//        tvShows.value = dummyTvShows
//
//        `when`(repository.getTvShowList()).thenReturn(tvShows)
//
//        viewModel.getTvShows().observeForever(observer)
//        verify(observer).onChanged(dummyTvShows)
    }
}
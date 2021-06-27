package com.khoiron14.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
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
 * Created by khoiron14 on 11/15/2019.
 */
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private val observer: Observer<List<Movie>?> = mock()
    private val dummyMovies: ArrayList<Movie> = FakeDataDummy().generateDummyMovie()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovieList() {
//        val movies: MutableLiveData<List<Movie>> = MutableLiveData()
//        movies.value = dummyMovies
//
//        `when`(repository.getMovieList()).thenReturn(movies)
//
//        viewModel.getMovies().observeForever(observer)
//        verify(observer).onChanged(dummyMovies)
    }
}
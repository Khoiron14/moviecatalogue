package com.khoiron14.moviecatalogue.ui.detail

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
 * Created by khoiron14 on 11/16/2019.
 */
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private val observer: Observer<Movie?> = mock()
    private val dummyMovie: Movie = FakeDataDummy().generateDummyMovie()[0]
    private val movieId: Int = dummyMovie.id

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)
        viewModel.movieId = movieId
    }

    @Test
    fun getMovie() {
        val movie: MutableLiveData<Movie> = MutableLiveData()
        movie.value = dummyMovie

        `when`(repository.getMovie(movieId)).thenReturn(movie)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}
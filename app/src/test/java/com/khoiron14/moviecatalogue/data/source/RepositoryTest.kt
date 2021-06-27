package com.khoiron14.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource
import com.khoiron14.moviecatalogue.utils.FakeDataDummy
import com.khoiron14.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.invocation.InvocationOnMock


/**
 * Created by khoiron14 on 11/27/2019.
 */
@Suppress("UNCHECKED_CAST")
class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository: FakeRepository = FakeRepository(remote)

    private val movieListResponses: ArrayList<Movie> = FakeDataDummy().generateDummyMovie()
    private val movieResponse: Movie = movieListResponses[0]
    private val tvShowListResponses: ArrayList<TvShow> = FakeDataDummy().generateDummyTvShow()
    private val tvShowResponse: TvShow = tvShowListResponses[0]

    @Test
    fun getMovieList() {
        doAnswer { invocation: InvocationOnMock ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieListResponses)
            null
        }.`when`(remote).getMovieList(any())

        val result: List<Movie>? = LiveDataTestUtil.getValue(repository.getMovieList())

        verify(
            remote,
            times(1)
        ).getMovieList(any())

        assertNotNull(result)
        assertEquals(movieListResponses.size, result?.size)
    }

    @Test
    fun getTvShowList() {
        doAnswer { invocation: InvocationOnMock ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowListResponses)
            null
        }.`when`(remote).getTvShowList(any())

        val result: List<TvShow>? = LiveDataTestUtil.getValue(repository.getTvShowList())

        verify(
            remote,
            times(1)
        ).getTvShowList(any())

        assertNotNull(result)
        assertEquals(tvShowListResponses.size, result?.size)
    }

    @Test
    fun getMovie() {
        doAnswer { invocation: InvocationOnMock ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                .onMovieReceived(movieResponse)
            null
        }.`when`(remote).getMovie(eq(movieResponse.id), any())

        val result: Movie? = LiveDataTestUtil.getValue(repository.getMovie(movieResponse.id))

        verify(
            remote,
            times(1)
        ).getMovie(eq(movieResponse.id), any())

        assertNotNull(result)
        assertEquals(movieResponse.title, result?.title)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation: InvocationOnMock ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback)
                .onTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(eq(tvShowResponse.id), any())

        val result: TvShow? = LiveDataTestUtil.getValue(repository.getTvShow(tvShowResponse.id))

        verify(
            remote,
            times(1)
        ).getTvShow(eq(tvShowResponse.id), any())

        assertNotNull(result)
        assertEquals(tvShowResponse.name, result?.name)
    }
}
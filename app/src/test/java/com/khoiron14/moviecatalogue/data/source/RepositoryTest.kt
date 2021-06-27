package com.khoiron14.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.khoiron14.moviecatalogue.data.source.local.LocalDataSource
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource
import com.khoiron14.moviecatalogue.utils.AppExecutors
import com.khoiron14.moviecatalogue.utils.FakeDataDummy
import com.khoiron14.moviecatalogue.utils.LiveDataTestUtil
import com.khoiron14.moviecatalogue.utils.PagedListUtil
import com.khoiron14.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


/**
 * Created by khoiron14 on 11/27/2019.
 */
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val appRepository = FakeRepository(remote, local, appExecutors)

    private val movies = FakeDataDummy().generateDummyMovie()
    private val movieId = movies[0].id
    private val tvShows = FakeDataDummy().generateDummyTvShow()
    private val tvShowId = tvShows[0].id

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        appRepository.getMovieList()

        val movieList = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy().generateDummyMovie()))
        verify(local).getAllMovies()
        assertNotNull(movieList.data)
        assertEquals(movies.size.toLong(), movieList.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        appRepository.getTvShowList()

        val tvShowList = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy().generateDummyTvShow()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowList.data)
        assertEquals(tvShows.size.toLong(), tvShowList.data?.size?.toLong())
    }

    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        appRepository.getFavoritedMovies()

        val movieList = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy().generateDummyMovie()))
        verify(local).getFavoritedMovies()
        assertNotNull(movieList)
        assertEquals(movies.size.toLong(), movieList.data?.size?.toLong())
    }

    @Test
    fun getFavoritedTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoritedTvShows()).thenReturn(dataSourceFactory)
        appRepository.getFavoritedTvShows()

        val tvShowList = Resource.success(PagedListUtil.mockPagedList(FakeDataDummy().generateDummyTvShow()))
        verify(local).getFavoritedTvShows()
        assertNotNull(tvShowList)
        assertEquals(tvShows.size.toLong(), tvShowList.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        val dummyMovie = FakeDataDummy().generateDummyMovie()[0]
        movie.value = MovieEntity(
            dummyMovie.id,
            dummyMovie.title,
            dummyMovie.posterPath,
            false
        )
        `when`(local.getMovie(movieId)).thenReturn(movie)

        val movieEntity = LiveDataTestUtil.getValue(appRepository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(FakeDataDummy().generateDummyMovie()[0].id, movieEntity.data?.movieId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        val dummyTvShow = FakeDataDummy().generateDummyTvShow()[0]
        tvShow.value = TvShowEntity(
            dummyTvShow.id,
            dummyTvShow.name,
            dummyTvShow.posterPath,
            false
        )
        `when`(local.getTvShow(tvShowId)).thenReturn(tvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(appRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(FakeDataDummy().generateDummyTvShow()[0].id, tvShowEntity.data?.tvShowId)
    }
}
package com.khoiron14.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.khoiron14.moviecatalogue.data.AppDataSource
import com.khoiron14.moviecatalogue.data.NetworkBoundResource
import com.khoiron14.moviecatalogue.data.source.local.LocalDataSource
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.data.source.remote.ApiResponse
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource
import com.khoiron14.moviecatalogue.utils.AppExecutors
import com.khoiron14.moviecatalogue.vo.Resource


/**
 * Created by khoiron14 on 11/26/2019.
 */
class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AppDataSource {

    override fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovieList()

            public override fun saveCallResult(data: List<Movie>) {
                val items = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(response.id,
                        response.title,
                        response.posterPath,
                        false)
                    items.add(movie)
                }

                localDataSource.insertMovies(items)
            }
        }.asLiveData()
    }

    override fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShowList()

            public override fun saveCallResult(data: List<TvShow>) {
                val items = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(response.id,
                        response.name,
                        response.posterPath,
                        false)
                    items.add(tvShow)
                }

                localDataSource.insertTvShows(items)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShows(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object: NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                remoteDataSource.getMovie(movieId)

            override fun saveCallResult(data: Movie) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.posterPath,
                    favorited = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object: NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> =
                remoteDataSource.getTvShow(tvShowId)

            override fun saveCallResult(data: TvShow) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.posterPath,
                    favorited = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }
}
package com.khoiron14.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khoiron14.moviecatalogue.data.source.local.LocalDataSource
import com.khoiron14.moviecatalogue.data.source.local.entity.MovieEntity
import com.khoiron14.moviecatalogue.data.source.local.entity.TvShowEntity
import com.khoiron14.moviecatalogue.data.source.remote.ApiResponse
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.utils.AppExecutors
import com.khoiron14.moviecatalogue.vo.Resource


/**
 * Created by khoiron14 on 11/26/2019.
 */
class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): AcademyDataSource {

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(remoteData, localData, appExecutors).apply {
                    INSTANCE = this
                }
            }
    }

    override fun getMovieList(): LiveData<Resource<List<MovieEntity>>> {
        return object: NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovieList()

            public override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.posterPath,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShowList(): LiveData<Resource<List<TvShowEntity>>> {
        return object: NetworkBoundResource<List<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShowList()

            public override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

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

    override fun getFavoritedMovies(): LiveData<List<MovieEntity>> =
        localDataSource.getFavoritedMovies()

    override fun getFavoritedTvShows(): LiveData<List<TvShowEntity>> =
        localDataSource.getFavoritedTvShows()

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }
}
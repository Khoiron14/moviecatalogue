package com.khoiron14.moviecatalogue.data.source

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khoiron14.moviecatalogue.data.AcademyDataSource
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource


/**
 * Created by khoiron14 on 11/26/2019.
 */
class FakeRepository(@NonNull val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    companion object {
        @Volatile
        private var INSTANCE: FakeRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FakeRepository? {
            if (INSTANCE == null) {
                synchronized(FakeRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FakeRepository(remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getMovieList(): LiveData<List<Movie>?> {
        val movieResults: MutableLiveData<List<Movie>?> = MutableLiveData()

        remoteDataSource.getMovieList(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<Movie>?) {
                movieResults.postValue(movieResponses)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return movieResults
    }

    override fun getTvShowList(): LiveData<List<TvShow>?> {
        val tvShowResults: MutableLiveData<List<TvShow>?> = MutableLiveData()

        remoteDataSource.getTvShowList(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShow>?) {
                tvShowResults.postValue(tvShowResponses)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return tvShowResults
    }

    override fun getMovie(movieId: Int?): LiveData<Movie?> {
        val movieResult: MutableLiveData<Movie?> = MutableLiveData()

        remoteDataSource.getMovie(movieId, object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieResponse: Movie?) {
                movieResult.postValue(movieResponse)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return movieResult
    }

    override fun getTvShow(tvShowId: Int?): LiveData<TvShow?> {
        val tvShowResult: MutableLiveData<TvShow?> = MutableLiveData()

        remoteDataSource.getTvShow(tvShowId, object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowResponse: TvShow?) {
                tvShowResult.postValue(tvShowResponse)
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return tvShowResult
    }
}
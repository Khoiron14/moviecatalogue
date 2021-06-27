package com.khoiron14.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import com.khoiron14.moviecatalogue.utils.EspressoIdlingResource
import com.khoiron14.moviecatalogue.utils.JsonHelper


/**
 * Created by khoiron14 on 11/25/2019.
 */
class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                RemoteDataSource(helper).apply { INSTANCE = this }
            }
    }

    fun getMovieList(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<Movie>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadMovieList())
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }

    fun getTvShowList(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShow>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTvShowList())
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }

    fun getMovie(movieId: Int): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Movie>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadMovie(movieId))
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }

    fun getTvShow(tvShowId: Int): LiveData<ApiResponse<TvShow>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShow>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTvShow(tvShowId))
            EspressoIdlingResource.decrement()
        },
            SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }
}
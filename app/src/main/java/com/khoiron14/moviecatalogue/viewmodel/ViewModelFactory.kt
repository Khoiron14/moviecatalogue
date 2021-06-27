package com.khoiron14.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.di.Injection
import com.khoiron14.moviecatalogue.ui.detail.MovieDetailViewModel
import com.khoiron14.moviecatalogue.ui.detail.TvShowDetailViewModel
import com.khoiron14.moviecatalogue.ui.movie.MovieViewModel
import com.khoiron14.moviecatalogue.ui.tvshow.TvShowViewModel


/**
 * Created by khoiron14 on 11/27/2019.
 */
class ViewModelFactory private constructor(private val repository: Repository) : NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> { MovieViewModel(repository) as T }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> { TvShowViewModel(repository) as T }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> { MovieDetailViewModel(repository) as T }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> { TvShowDetailViewModel(repository) as T }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
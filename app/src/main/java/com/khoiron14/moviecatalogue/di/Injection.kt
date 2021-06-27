package com.khoiron14.moviecatalogue.di

import android.content.Context
import com.khoiron14.moviecatalogue.data.Repository
import com.khoiron14.moviecatalogue.data.source.local.LocalDataSource
import com.khoiron14.moviecatalogue.data.source.local.room.AppDatabase
import com.khoiron14.moviecatalogue.data.source.remote.RemoteDataSource
import com.khoiron14.moviecatalogue.utils.AppExecutors
import com.khoiron14.moviecatalogue.utils.JsonHelper


/**
 * Created by khoiron14 on 11/27/2019.
 */
object Injection {
    fun provideRepository(context: Context): Repository {

        val database = AppDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
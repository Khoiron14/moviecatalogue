package com.khoiron14.moviecatalogue.data.source.remote.service

import com.khoiron14.moviecatalogue.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by khoiron14 on 7/22/2019.
 */
object RetrofitFactory {
    fun service(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
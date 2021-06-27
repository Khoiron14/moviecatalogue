package com.khoiron14.moviecatalogue.data.source.remote.response

import com.squareup.moshi.Json

/**
 * Created by khoiron14 on 7/3/2019.
 */
data class TvShow(
    var id: Int,
    @field:Json(name = "original_name") var name: String,
    @field:Json(name = "vote_average") var rating: String,
    @field:Json(name = "first_air_date") var firstAirDate: String,
    var overview: String,
    @field:Json(name = "poster_path") var posterPath: String,
    @field:Json(name = "backdrop_path") var backdropPath: String
)
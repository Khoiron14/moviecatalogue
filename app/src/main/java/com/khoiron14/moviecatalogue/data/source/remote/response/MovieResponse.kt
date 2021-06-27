package com.khoiron14.moviecatalogue.data.source.remote.response

/**
 * Created by khoiron14 on 7/22/2019.
 */
data class MovieResponse(
    val page: Int? = null,
    val total_results: Int? = null,
    val total_pages: Int? = null,
    val results: List<Movie>? = null
)
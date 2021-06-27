package com.khoiron14.moviecatalogue.utils

import android.content.Context
import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


/**
 * Created by khoiron14 on 11/26/2019.
 */
class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is`: InputStream = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovieList(): List<Movie> {
        val list: ArrayList<Movie> = arrayListOf()
        try {
            val responseObject = JSONObject(parsingFileToString("movie_list.json")!!)
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)
                val id = movie.getInt("id")
                val title = movie.getString("original_title")
                val rating = movie.getString("vote_average")
                val releaseDate = movie.getString("release_date")
                val overview = movie.getString("overview")
                val posterPath = movie.getString("poster_path")
                val backdropPath = movie.getString("backdrop_path")
                val movieResponse =
                    Movie(id, title, rating, releaseDate, overview, posterPath, backdropPath)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShowList(): List<TvShow> {
        val list: ArrayList<TvShow> = arrayListOf()
        try {
            val responseObject = JSONObject(parsingFileToString("tv_show_list.json")!!)
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)
                val id = tvShow.getInt("id")
                val name = tvShow.getString("original_name")
                val rating = tvShow.getString("vote_average")
                val firstAirDate = tvShow.getString("first_air_date")
                val overview = tvShow.getString("overview")
                val posterPath = tvShow.getString("poster_path")
                val backdropPath = tvShow.getString("backdrop_path")
                val tvShowResponse =
                    TvShow(id, name, rating, firstAirDate, overview, posterPath, backdropPath)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadMovie(movieId: Int): Movie {
        val fileName = String.format("movie_%s.json", movieId)
        var movie: Movie? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val movieResponse = JSONObject(result)
                movie = Movie(
                    movieResponse.getInt("id"),
                    movieResponse.getString("original_title"),
                    movieResponse.getString("vote_average"),
                    movieResponse.getString("release_date"),
                    movieResponse.getString("overview"),
                    movieResponse.getString("poster_path"),
                    movieResponse.getString("backdrop_path")
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movie as Movie
    }

    fun loadTvShow(tvShowId: Int): TvShow {
        val fileName = String.format("tv_show_%s.json", tvShowId)
        var tvShow: TvShow? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val tvShowResponse = JSONObject(result)
                tvShow = TvShow(
                    tvShowResponse.getInt("id"),
                    tvShowResponse.getString("original_name"),
                    tvShowResponse.getString("vote_average"),
                    tvShowResponse.getString("first_air_date"),
                    tvShowResponse.getString("overview"),
                    tvShowResponse.getString("poster_path"),
                    tvShowResponse.getString("backdrop_path")
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvShow as TvShow
    }
}
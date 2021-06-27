package com.khoiron14.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by khoiron14 on 7/28/2019.
 */
@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_MOVIE_ID) var movieId: Int,

    @ColumnInfo(name = COLUMN_TITLE) var title: String,

    @ColumnInfo(name = COLUMN_POSTER_PATH) var posterPath: String,

    @ColumnInfo(name = COLUMN_FAVORITED) var favorited: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "movieFavorite"
        const val COLUMN_MOVIE_ID = "movieId"
        const val COLUMN_TITLE = "title"
        const val COLUMN_POSTER_PATH = "posterPath"
        const val COLUMN_FAVORITED = "favorited"
    }
}
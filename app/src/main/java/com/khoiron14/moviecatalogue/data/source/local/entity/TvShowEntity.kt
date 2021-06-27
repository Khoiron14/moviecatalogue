package com.khoiron14.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by khoiron14 on 7/28/2019.
 */
@Entity(tableName = TvShowEntity.TABLE_NAME)
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_TV_SHOW_ID) var tvShowId: Int,

    @ColumnInfo(name = COLUMN_NAME) var name: String,

    @ColumnInfo(name = COLUMN_POSTER_PATH) var posterPath: String,

    @ColumnInfo(name = COLUMN_FAVORITED) var favorited: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "tvShowFavorite"
        const val COLUMN_TV_SHOW_ID = "tvShowId"
        const val COLUMN_NAME = "name"
        const val COLUMN_POSTER_PATH = "posterPath"
        const val COLUMN_FAVORITED = "favorited"
    }
}
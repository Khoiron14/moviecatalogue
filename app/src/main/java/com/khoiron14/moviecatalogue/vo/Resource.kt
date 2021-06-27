package com.khoiron14.moviecatalogue.vo

import com.khoiron14.moviecatalogue.vo.Status.SUCCESS
import com.khoiron14.moviecatalogue.vo.Status.ERROR
import com.khoiron14.moviecatalogue.vo.Status.LOADING

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(ERROR, data, msg)

        fun <T> loading(data: T?): Resource<T> = Resource(LOADING, data, null)
    }
}
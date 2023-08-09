package com.areeb.noteapp.data.network

sealed class Resources<out T> {
    data class Success<out T>(val data: T) : Resources<T>()
    data class Error(val error: String) : Resources<Nothing>()
    data class Loading(val status: Boolean? = null, val tag: String? = null) : Resources<Nothing>()
}

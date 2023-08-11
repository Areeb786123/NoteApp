package com.areeb.noteapp.ui.common

interface RecyclerItemClick<T> {
    fun onClick(t: T)
    fun onDelete(t: T)
}

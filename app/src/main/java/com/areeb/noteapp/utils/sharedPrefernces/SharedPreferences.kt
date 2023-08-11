package com.areeb.noteapp.utils.sharedPrefernces

import android.content.Context
import com.areeb.noteapp.utils.Constants.Companion.DARK_MODE
import com.areeb.noteapp.utils.Constants.Companion.SHARED_PREFERENCES

object SharedPreferences {

    fun setDarkModeValue(context: Context, value: Boolean) {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putBoolean(DARK_MODE, value).apply()
    }

    fun getDarkModeValue(context: Context): Boolean {
        val sp = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return sp.getBoolean(DARK_MODE, false)
    }
}

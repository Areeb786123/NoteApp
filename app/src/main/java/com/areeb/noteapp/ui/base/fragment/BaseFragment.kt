package com.areeb.noteapp.ui.base.fragment

import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun enableOrDisableDarkMode(isEnable: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (!isEnable) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES)

//        restartApp()
        // Recreate the activity to apply the new theme
    }
}

package com.areeb.noteapp.ui.setting.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {

    private var _isDarkEnable = MutableLiveData<Boolean>()
    val isDarkModeEnable: LiveData<Boolean> get() = _isDarkEnable

    fun setDarkMode(isEnable: Boolean) {
        _isDarkEnable.value = isEnable
    }
}

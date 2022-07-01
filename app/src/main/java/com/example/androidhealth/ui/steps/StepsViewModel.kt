package com.example.androidhealth.ui.steps

import android.app.Application
import android.app.UiModeManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StepsViewModel(application: Application) : AndroidViewModel(application) {

    private val _nightModeEnabled = MutableLiveData<Boolean>().apply {
        val mode = application.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        value = mode == Configuration.UI_MODE_NIGHT_YES
    }
    val nightMode: LiveData<Boolean> = _nightModeEnabled

    fun toggleNightMode(enabled: Boolean) {
        _nightModeEnabled.value = enabled
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) {
                UiModeManager.MODE_NIGHT_YES
            } else {
                UiModeManager.MODE_NIGHT_NO
            }
        )
    }
}
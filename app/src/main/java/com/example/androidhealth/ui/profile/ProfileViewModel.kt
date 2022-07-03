package com.example.androidhealth.ui.profile

import android.app.UiModeManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhealth.interactor.UserSettingsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val interactor: UserSettingsInteractor
) : ViewModel() {

    private val _nightMode = MutableStateFlow(UiModeManager.MODE_NIGHT_AUTO)
    val nightMode: StateFlow<Int> get() = _nightMode.asStateFlow()

    init {
        viewModelScope.launch {
            interactor.isDarkThemeEnabled().collect {
                _nightMode.value = it
            }
        }
    }

    fun toggleNightMode(enabled: Boolean) {
        val mode = if (enabled) {
            UiModeManager.MODE_NIGHT_YES
        } else {
            UiModeManager.MODE_NIGHT_NO
        }
        _nightMode.value = mode
        viewModelScope.launch {
            interactor.setIsDarkThemeEnabled(mode)
        }
    }
}
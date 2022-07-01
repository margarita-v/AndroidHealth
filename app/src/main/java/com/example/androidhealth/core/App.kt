package com.example.androidhealth.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidhealth.interactor.UserSettingsInteractor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var interactor: UserSettingsInteractor

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(interactor.isDarkThemeEnabledBlocking())
    }
}
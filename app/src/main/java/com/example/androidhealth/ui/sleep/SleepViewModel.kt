package com.example.androidhealth.ui.sleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SleepViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sleep Fragment"
    }
    val text: LiveData<String> = _text
}
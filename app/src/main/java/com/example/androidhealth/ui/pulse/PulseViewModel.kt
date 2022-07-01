package com.example.androidhealth.ui.pulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PulseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pulse Fragment"
    }
    val text: LiveData<String> = _text
}
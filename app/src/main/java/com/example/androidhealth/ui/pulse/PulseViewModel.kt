package com.example.androidhealth.ui.pulse

import androidx.lifecycle.ViewModel
import com.example.androidhealth.R
import com.example.androidhealth.ui.view.LineScaleChartView.UiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PulseViewModel : ViewModel() {

    private val _pulseData = MutableStateFlow(
        listOf(
            UiData(width = 224, color = R.color.errigal_white),
            UiData(width = 16, color = R.color.jovial_jade),
            UiData(width = 16, color = R.color.corona),
            UiData(width = 48, color = R.color.apocalyptic_orange)
        )
    )
    val pulseData: StateFlow<List<UiData>> get() = _pulseData.asStateFlow()
}
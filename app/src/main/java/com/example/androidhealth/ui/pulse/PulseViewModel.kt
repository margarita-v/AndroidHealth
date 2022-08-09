package com.example.androidhealth.ui.pulse

import androidx.lifecycle.ViewModel
import com.example.androidhealth.domain.PulseInfoType
import com.example.androidhealth.ui.view.LineScaleChartView.UiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class PulseViewModel : ViewModel() {

    private val _pulseData = MutableStateFlow<List<UiData>>(listOf())
    val pulseData: StateFlow<List<UiData>> get() = _pulseData.asStateFlow()

    init {
        _pulseData.value = PulseInfoType.values().map {
            UiData(width = Random.nextInt(0, 200), color = it.colorResId)
        }
    }
}
package com.example.androidhealth.ui.pulse

import androidx.lifecycle.ViewModel
import com.example.androidhealth.domain.PulseInfoType
import com.example.androidhealth.ui.view.LineScaleChartView.UiData
import com.example.androidhealth.ui.view.PulseValues
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class PulseViewModel : ViewModel() {

    private val _pulseData = MutableStateFlow<List<UiData>>(listOf())
    val pulseData: StateFlow<List<UiData>> get() = _pulseData.asStateFlow()

    private val _pulseValues =
        MutableStateFlow<Pair<PulseValues, PulseValues>>(listOf<Int>() to listOf())
    val pulseValues: StateFlow<Pair<PulseValues, PulseValues>> get() = _pulseValues.asStateFlow()

    init {
        _pulseData.value = PulseInfoType.values().map {
            UiData(width = Random.nextInt(0, 200), color = it.colorResId)
        }
        _pulseValues.value = listOf(80, 120, 10) to listOf(100, 50, 120, 10, 40)
    }
}
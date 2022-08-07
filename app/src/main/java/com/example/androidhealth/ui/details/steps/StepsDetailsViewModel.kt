package com.example.androidhealth.ui.details.steps

import androidx.lifecycle.ViewModel
import com.patrykandpatryk.vico.core.entry.FloatEntry
import com.patrykandpatryk.vico.core.util.RandomEntriesGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

class StepsDetailsViewModel : ViewModel() {

    private val calendar = Calendar.getInstance()
    private val days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    private val generator = RandomEntriesGenerator(
        xRange = IntProgression.fromClosedRange(0, 24, 2),
        yRange = 0..1000,
    )
    private val stepsData = (0..days).map {
        val entries = generator.generateRandomEntries()
        val sum = entries.sumOf { it.y.toInt() }
        StepsPerDayModel(
            entries = entries,
            sum = sum,
            left = 10000 - sum
        )
    }

    private val currentDay: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)

    private var currentDayOffset = 0
        set(value) {
            field = value
            calendar.add(Calendar.DATE, value)
            val day = currentDay
            _canGoToNextDay.value = day < days
            _canGoToPreviousDay.value = day > 1
            _currentChartData.value = stepsData[currentDay - 1]
            _currentDayTitle.value = renderFormattedDay(calendar)
        }

    private val _canGoToNextDay = MutableStateFlow(currentDay < days)
    val canGoToNextDay: StateFlow<Boolean> get() = _canGoToNextDay.asStateFlow()

    private val _canGoToPreviousDay = MutableStateFlow(currentDay > 1)
    val canGoToPreviousDay: StateFlow<Boolean> get() = _canGoToPreviousDay.asStateFlow()

    private val _currentDayTitle = MutableStateFlow(renderFormattedDay(calendar))
    val currentDayTitle: StateFlow<String> get() = _currentDayTitle.asStateFlow()

    private val _currentChartData = MutableStateFlow(stepsData.first())
    val currentChartData: StateFlow<StepsPerDayModel> get() = _currentChartData.asStateFlow()

    fun goToNextDay() {
        currentDayOffset = 1
    }

    fun goToPreviousDay() {
        currentDayOffset = -1
    }

    private fun renderFormattedDay(calendar: Calendar): String =
        SimpleDateFormat("d MMMM", Locale("ru")).format(calendar.time)
}

data class StepsPerDayModel(
    val entries: List<FloatEntry>,
    val sum: Int,
    val left: Int
)
package com.example.androidhealth.ui.details.steps

import androidx.lifecycle.ViewModel
import com.patrykandpatryk.vico.core.entry.FloatEntry
import com.patrykandpatryk.vico.core.util.RandomEntriesGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class StepsDetailsViewModel : ViewModel() {

    // is started from the current date and is updated for each render change
    private val calendar = Calendar.getInstance()
    private val startDate = calendar.get(Calendar.DAY_OF_MONTH)
    private val lastMonth = calendar.get(Calendar.MONTH) - 1
    private val generator = RandomEntriesGenerator(
        xRange = IntProgression.fromClosedRange(0, 24, 2),
        yRange = 100..1000,
    )
    private val stepsData = (0..daysBetween()).map {
        val entries = generator.generateRandomEntries()
        val sum = entries.sumOf { it.y.toInt() }
        StepsPerDayModel(
            entries = entries,
            sum = sum,
            left = (10000 - sum).coerceAtLeast(0)
        )
    }

    private val currentDay: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)

    private val currentMonth: Int
        get() = calendar.get(Calendar.MONTH)

    private var currentDayOffset = 0
        set(value) {
            field = value
            calendar.add(Calendar.DATE, value)
            val day = currentDay
            val month = currentMonth
            _canGoToNextDay.value = month == lastMonth || day < startDate
            _canGoToPreviousDay.value = day != startDate || month != lastMonth
            _currentChartData.value = stepsData[currentDay - 1]
            _currentDayTitle.value = renderFormattedDay(calendar)
        }

    private val _canGoToNextDay = MutableStateFlow(false)
    val canGoToNextDay: StateFlow<Boolean> get() = _canGoToNextDay.asStateFlow()

    private val _canGoToPreviousDay = MutableStateFlow(true)
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

    /** Calculates a count of days between the current date and the same from the last month */
    private fun daysBetween(): Long {
        val calendar = Calendar.getInstance()
        val current = calendar.timeInMillis
        calendar.add(Calendar.MONTH, -1)
        return TimeUnit.MILLISECONDS.toDays(current - calendar.timeInMillis)
    }

    private fun renderFormattedDay(calendar: Calendar): String =
        SimpleDateFormat("d MMMM", Locale("ru")).format(calendar.time)
}

data class StepsPerDayModel(
    val entries: List<FloatEntry>,
    val sum: Int,
    val left: Int
)
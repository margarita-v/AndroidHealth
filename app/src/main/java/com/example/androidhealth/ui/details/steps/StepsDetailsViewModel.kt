package com.example.androidhealth.ui.details.steps

import androidx.lifecycle.ViewModel
import com.example.androidhealth.interactor.StepsDataInteractor
import com.patrykandpatryk.vico.core.entry.FloatEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StepsDetailsViewModel @Inject constructor(
    private val interactor: StepsDataInteractor
) : ViewModel() {

    // is started from the current date and is updated for each render change
    private val calendar = Calendar.getInstance()
    private val startDate = calendar.get(Calendar.DAY_OF_MONTH)
    private val lastMonth = calendar.get(Calendar.MONTH) - 1
    private val stepsData by lazy { interactor.stepsData }

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

    private val _currentChartData = MutableStateFlow(stepsData[currentDay - 1])
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
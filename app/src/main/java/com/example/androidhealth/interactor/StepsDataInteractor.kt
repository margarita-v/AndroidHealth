package com.example.androidhealth.interactor

import com.example.androidhealth.ui.details.steps.StepsPerDayModel
import com.example.androidhealth.utils.STEPS_RECOMMENDED
import com.patrykandpatryk.vico.core.util.RandomEntriesGenerator
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StepsDataInteractor @Inject constructor() {

    private val generator = RandomEntriesGenerator(
        xRange = IntProgression.fromClosedRange(0, 24, 2),
        yRange = 100..500,
    )
    var stepsData = listOf<StepsPerDayModel>()
        private set

    fun generate() {
        stepsData = (0..daysBetween()).map {
            val entries = generator.generateRandomEntries()
            val sum = entries.sumOf { it.y.toInt() }
            StepsPerDayModel(
                entries = entries,
                sum = sum,
                left = (STEPS_RECOMMENDED - sum).coerceAtLeast(0)
            )
        }
    }

    /** Calculates a count of days between the current date and the same from the last month */
    private fun daysBetween(): Long {
        val calendar = Calendar.getInstance()
        val current = calendar.timeInMillis
        calendar.add(Calendar.MONTH, -1)
        return TimeUnit.MILLISECONDS.toDays(current - calendar.timeInMillis)
    }
}
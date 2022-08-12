package com.example.androidhealth.ui.steps

import androidx.lifecycle.ViewModel
import com.example.androidhealth.interactor.StepsDataInteractor
import com.patrykandpatryk.vico.core.util.RandomEntriesGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StepsViewModel @Inject constructor(
    interactor: StepsDataInteractor
) : ViewModel() {

    private val generator = RandomEntriesGenerator(
        xRange = 1..7,
        yRange = 0..5,
    )
    val entries = generator.generateRandomEntries()
    val average = entries.map { it.y }.average()
    val currentSteps = interactor.stepsData[Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1]
}
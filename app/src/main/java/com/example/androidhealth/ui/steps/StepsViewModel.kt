package com.example.androidhealth.ui.steps

import androidx.lifecycle.ViewModel
import com.patrykandpatryk.vico.core.util.RandomEntriesGenerator

class StepsViewModel : ViewModel() {

    private val generator = RandomEntriesGenerator(
        xRange = 1..7,
        yRange = 0..5,
    )
    val entries = generator.generateRandomEntries()
    val average = entries.map { it.y }.average()
}
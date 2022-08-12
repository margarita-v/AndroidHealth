package com.example.androidhealth.ui.details.steps

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentStepsDetailsBinding
import com.example.androidhealth.ui.details.chart.getMarker
import com.example.androidhealth.utils.formatSteps
import com.example.androidhealth.utils.resolveAttrColor
import com.example.androidhealth.utils.resolveColor
import com.patrykandpatryk.vico.core.axis.Axis
import com.patrykandpatryk.vico.core.axis.AxisPosition
import com.patrykandpatryk.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatryk.vico.core.chart.line.LineChart
import com.patrykandpatryk.vico.core.chart.values.ChartValues
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.formatter.ValueFormatter
import kotlinx.coroutines.launch

class StepsDetailsFragment : Fragment(R.layout.fragment_steps_details) {

    private var binding: FragmentStepsDetailsBinding? = null

    private val viewModel by viewModels<StepsDetailsViewModel>()

    private val producer = ChartEntryModelProducer()
    private val valueFormatter = HoursValueFormatter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStepsDetailsBinding.bind(view)
        this.binding = binding

        lifecycleScope.launch {
            launch {
                viewModel.canGoToPreviousDay.collect {
                    binding.stepsLeftIbtn.isInvisible = !it
                }
            }
            launch {
                viewModel.canGoToNextDay.collect {
                    binding.stepsRightIbtn.isInvisible = !it
                }
            }
            launch {
                viewModel.currentDayTitle.collect {
                    binding.stepsDateTv.text = it
                }
            }
            launch {
                viewModel.currentChartData.collect {
                    producer.setEntries(it.entries)
                    binding.stepsCountTv.text = formatSteps(it.sum)
                    binding.stepsCountLeftTv.isVisible = it.left > 0
                    binding.stepsCountLeftTv.text = getString(
                        R.string.title_steps_left,
                        formatSteps(it.left)
                    )
                }
            }
        }
        binding.stepsLeftIbtn.setOnClickListener { viewModel.goToPreviousDay() }
        binding.stepsRightIbtn.setOnClickListener { viewModel.goToNextDay() }

        initChart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initChart() {
        binding?.stepsChart?.apply {
            entryProducer = producer
            (bottomAxis as Axis).guideline = null
            (bottomAxis as Axis).valueFormatter = valueFormatter

            val mainBackground = context.resolveColor(R.color.mainBackground)
            marker = getMarker(
                labelColor = mainBackground,
                bubbleColor = context.resolveColor(R.color.white),
                indicatorInnerColor = mainBackground,
                guidelineColor = context.resolveAttrColor(android.R.attr.textColorPrimary),
            )
            (chart as LineChart).spacingDp = 60f
        }
    }
}

private class HoursValueFormatter
    : AxisValueFormatter<AxisPosition.Horizontal.Bottom>, ValueFormatter {

    override fun formatValue(value: Float, chartValues: ChartValues): CharSequence {
        val start = if (value < 10) "0${value.toInt()}" else value.toInt().toString()
        return "$start:00"
    }
}
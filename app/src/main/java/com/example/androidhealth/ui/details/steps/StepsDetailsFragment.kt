package com.example.androidhealth.ui.details.steps

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhealth.R
import com.example.androidhealth.databinding.FragmentStepsDetailsBinding
import com.example.androidhealth.ui.details.chart.getMarker
import com.example.androidhealth.utils.resolveAttrColor
import com.example.androidhealth.utils.resolveColor
import com.patrykandpatryk.vico.core.axis.Axis
import com.patrykandpatryk.vico.core.axis.AxisPosition
import com.patrykandpatryk.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatryk.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatryk.vico.core.chart.line.LineChart
import com.patrykandpatryk.vico.core.chart.values.ChartValues
import com.patrykandpatryk.vico.core.component.shape.ShapeComponent
import com.patrykandpatryk.vico.core.component.text.textComponent
import com.patrykandpatryk.vico.core.dimensions.MutableDimensions
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

            val accentColor = context.resolveColor(R.color.accent)
            val mainBackground = context.resolveColor(R.color.mainBackground)
            marker = getMarker(
                labelColor = mainBackground,
                bubbleColor = context.resolveColor(R.color.onSecondary),
                indicatorInnerColor = mainBackground,
                guidelineColor = context.resolveAttrColor(android.R.attr.textColorPrimary),
            )
            (chart as LineChart).spacingDp = 60f
            chart?.maxY = 10000f
            chart?.addDecoration(
                decoration = ThresholdLine(
                    thresholdValue = 8000f,
                    thresholdLabel = "8 000",
                    minimumLineThicknessDp = 2f,
                    labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.End,
                    labelComponent = textComponent {
                        color = accentColor
                        textSizeSp = 12f
                        margins = MutableDimensions(0f, 0f, 20f, 3f)
                    },
                    lineComponent = ShapeComponent(color = accentColor),
                )
            )
        }
        producer.setEntries(viewModel.generator.generateRandomEntries())
    }
}

private class HoursValueFormatter
    : AxisValueFormatter<AxisPosition.Horizontal.Bottom>, ValueFormatter {

    override fun formatValue(value: Float, chartValues: ChartValues): CharSequence {
        val start = if (value < 10) "0${value.toInt()}" else value.toInt().toString()
        return "$start:00"
    }
}
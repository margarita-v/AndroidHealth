package com.example.androidhealth.ui.steps

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhealth.R
import com.example.androidhealth.RootNavGraphDirections
import com.example.androidhealth.databinding.FragmentStepsBinding
import com.example.androidhealth.ui.view.StepsCircleChartView
import com.example.androidhealth.utils.*
import com.patrykandpatryk.vico.core.chart.column.ColumnChart
import com.patrykandpatryk.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatryk.vico.core.component.shape.LineComponent
import com.patrykandpatryk.vico.core.component.shape.ShapeComponent
import com.patrykandpatryk.vico.core.component.shape.Shapes
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer

class StepsFragment : Fragment(R.layout.fragment_steps) {

    private var binding: FragmentStepsBinding? = null

    private val viewModel by viewModels<StepsViewModel>()

    private val producer = ChartEntryModelProducer()

    private val circleView by lazy { StepsCircleChartView(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStepsBinding.bind(view)
        this.binding = binding

        binding.detailsBtn.setOnClickListener {
            openFullScreen(RootNavGraphDirections.toDetails())
        }

        val averageFormatted = "%.2f".format(viewModel.average)
        binding.distanceMessageTv.text =
            getString(R.string.steps_distance_message, averageFormatted)

        val distance = arrayOf(
            SpannableString(averageFormatted),
            SpannableString(getString(R.string.title_average_distance_value))
                .applyTextAppearance(requireContext(), R.style.Text_Regular_14_SecondaryColor)
        )
        binding.averageDistanceValueTv.text = TextUtils.concat(*distance)

        producer.setEntries(viewModel.entries)

        binding.stepsCardView.render(
            value = formatSteps(viewModel.currentSteps),
            message = getString(
                R.string.title_tab_steps_message,
                formatSteps(STEPS_RECOMMENDED - viewModel.currentSteps)
            ),
            customView = circleView.apply {
                data = StepsCircleChartView.UiData(current = viewModel.currentSteps)
            }
        )
        initChart()
    }

    private fun initChart() {
        binding?.stepsAverageChart?.apply {
            entryProducer = producer
            (chart as ColumnChart).columns = listOf(
                LineComponent(
                    color = context.resolveColor(R.color.stepsAverageChartColor),
                    thicknessDp = 16f,
                    shape = Shapes.pillShape
                )
            )
            chart?.addDecoration(
                decoration = ThresholdLine(
                    thresholdValue = viewModel.average.toFloat(),
                    thresholdLabel = "",
                    minimumLineThicknessDp = 2f,
                    labelHorizontalPosition = ThresholdLine.LabelHorizontalPosition.End,
                    lineComponent = ShapeComponent(
                        shape = Shapes.pillShape,
                        color = context.resolveColor(R.color.accent)
                    ),
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
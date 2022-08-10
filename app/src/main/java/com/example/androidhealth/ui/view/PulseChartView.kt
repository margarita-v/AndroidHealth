package com.example.androidhealth.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import com.example.androidhealth.R
import com.example.androidhealth.utils.resolveColor

typealias PulseValues = List<Int>

private const val maxHeight = 150f
private const val startX = 10f
private const val diffX = 30f

/** A view which shows pulse values as a line chart */
class PulseChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var data: Pair<PulseValues, PulseValues> = listOf<Int>() to listOf()
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 12f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
    }
    private val path = Path()
    private var currentX = startX

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        setMeasuredDimension(measuredWidth, maxHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var points = data.first.mapToPoints()
        drawPath(points = points, canvas = canvas, colorResId = R.color.commonFirstChartColor)

        val startPoint = points.last()
        points = data.second.mapToPoints().toMutableList().apply {
            add(0, startPoint)
        }
        drawPath(points = points, canvas = canvas, colorResId = R.color.commonSecondChartColor)
    }

    private fun PulseValues.mapToPoints(): List<Pair<Float, Float>> =
        map {
            (currentX to it.toFloat().coerceIn(startX, maxHeight - diffX)).also {
                currentX += diffX
            }
        }

    private fun drawPath(
        points: List<Pair<Float, Float>>,
        canvas: Canvas,
        @ColorRes colorResId: Int
    ) {
        path.reset()
        points.forEachIndexed { index, point ->
            if (index == 0) {
                path.moveTo(point.first, point.second)
            } else {
                path.lineTo(point.first, point.second)
            }
        }
        paint.color = context.resolveColor(colorResId)
        canvas.drawPath(path, paint)
    }
}
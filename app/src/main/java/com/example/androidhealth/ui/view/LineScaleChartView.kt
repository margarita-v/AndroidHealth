package com.example.androidhealth.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.androidhealth.R
import com.example.androidhealth.utils.resolveColor

private const val cornerRadius = 80f

/** A line chart view which shows percentages of given values */
class LineScaleChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val rect = RectF(0f, 100f, 200f, 180f)
    private val path = Path()

    private val startCorners = floatArrayOf(
        cornerRadius, cornerRadius,   // Top left radius in px
        0f, 0f,                       // Top right radius in px
        0f, 0f,                       // Bottom right radius in px
        cornerRadius, cornerRadius    // Bottom left radius in px
    )
    private val endCorners = floatArrayOf(
        0f, 0f,                       // Top left radius in px
        cornerRadius, cornerRadius,   // Top right radius in px
        cornerRadius, cornerRadius,   // Bottom right radius in px
        0f, 0f                        // Bottom left radius in px
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val data = listOf(224, 16, 48)
        val modifier = measuredWidth.toFloat() / data.sum()
        val renderData = data.map { it.toFloat() * modifier }
        var previousWidth = 0f
        renderData.forEachIndexed { index, width ->
            val rect = rect.apply { right = width }
            path.reset()
            when {
                index == 0 -> {
                    paint.color = context.resolveColor(R.color.errigal_white)
                    path.addRoundRect(rect, startCorners, Path.Direction.CW)
                }
                index < data.lastIndex -> {
                    paint.color = context.resolveColor(R.color.jovial_jade)
                    canvas.translate(previousWidth, 0f)
                    path.addRect(rect, Path.Direction.CW)
                }
                else -> {
                    paint.color = context.resolveColor(R.color.corona)
                    canvas.translate(previousWidth, 0f)
                    path.addRoundRect(rect, endCorners, Path.Direction.CW)
                }
            }
            canvas.drawPath(path, paint)
            previousWidth = width
        }
    }
}
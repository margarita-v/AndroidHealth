package com.example.androidhealth.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import com.example.androidhealth.utils.resolveColor
import com.example.androidhealth.utils.toPx

private const val cornerRadius = 80f
private val minWidth = 1.toPx

/** A line chart view which shows percentages of given values */
class LineScaleChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var data: List<UiData> = listOf()
        set(value) {
            field = value.map {
                val width = it.width
                if (width > 0) {
                    it.copy(width = it.width.coerceAtLeast(minWidth))
                } else {
                    it
                }
            }
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val rect = RectF(0f, 0f, 0f, 80f)
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

        val modifier = measuredWidth.toFloat() / data.sumOf { it.width }
        val renderData = data.map { it.width.toFloat() * modifier }
        var previousWidth = 0f
        renderData.forEachIndexed { index, width ->
            val rect = rect.apply { right = width }
            paint.color = context.resolveColor(data[index].color)
            path.reset()
            when {
                index == 0 -> {
                    path.addRoundRect(rect, startCorners, Path.Direction.CW)
                }
                index < data.lastIndex -> {
                    canvas.translate(previousWidth, 0f)
                    path.addRect(rect, Path.Direction.CW)
                }
                else -> {
                    canvas.translate(previousWidth, 0f)
                    path.addRoundRect(rect, endCorners, Path.Direction.CW)
                }
            }
            canvas.drawPath(path, paint)
            previousWidth = width
        }
    }

    data class UiData(
        val width: Int,
        @ColorRes val color: Int
    )
}
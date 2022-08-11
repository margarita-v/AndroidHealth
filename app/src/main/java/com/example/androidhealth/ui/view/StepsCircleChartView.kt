package com.example.androidhealth.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.androidhealth.R
import com.example.androidhealth.utils.STEPS_RECOMMENDED
import com.example.androidhealth.utils.resolveColor
import com.example.androidhealth.utils.toPx

private const val size = 250
private const val maxAngle = 360f
private const val offset = 20f
private const val radius = 80f

/** A circle chart for two dimensions with different colors */
class StepsCircleChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    var data: UiData = UiData()
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 7f.toPx
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }
    private val oval = RectF(offset, offset, radius.toPx, radius.toPx)

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val diff = maxAngle / data.max

        paint.color = context.resolveColor(R.color.commonFirstChartColor)
        canvas.drawArc(oval, 0f, maxAngle, false, paint)

        paint.color = context.resolveColor(R.color.commonSecondChartColor)
        canvas.drawArc(oval, -95f, data.current * diff, false, paint)
    }

    data class UiData(
        val current: Int = 0,
        val max: Int = STEPS_RECOMMENDED
    )
}
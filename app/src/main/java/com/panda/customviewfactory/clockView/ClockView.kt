package com.panda.customviewfactory.clockView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.panda.customviewfactory.R
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * @ClassName ClockView
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/1/5 下午4:19
 * @Version 1.0
 */
class ClockView : View {
    var axisPaint: Paint = Paint()
    var axisSubPaint: Paint = Paint()
    var bgPaint: Paint = Paint()
    var dialPaint: Paint = Paint()
    var scalePaint: Paint = Paint()
    var hourHandPaint: Paint = Paint()
    var minuteHandPaint: Paint = Paint()
    var secondHandPaint: Paint = Paint()
    var bgColor: Int = 0
    var hourHandColor: Int = Color.WHITE
    var minuteHandColor: Int = Color.WHITE
    var secondHandColor: Int = Color.WHITE
    var dialColor: Int = Color.BLACK
    var scaleColor: Int = Color.parseColor("#0FCCCE")
    var scaleRadios: Float = 8f
    var scalePadding: Float = 60f
    var axisRadios: Float = 35f
    var secondHandLength: Float = 500f
    var minuteHandLength: Float = 400f
    var hourHandLength: Float = 300f
    var secondHandWidth: Float = 5f
    var minuteHandWidth: Float = 10f
    var hourHandWidth: Float = 15f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            getMeasureResult(widthMeasureSpec),
            getMeasureResult(heightMeasureSpec)
        )
    }

    private fun getMeasureResult(measureSpec: Int): Int {
        val defaultSize = 300
        val size: Int = MeasureSpec.getSize(measureSpec)
        when (MeasureSpec.getMode(measureSpec)) {
            //大小未知
            MeasureSpec.UNSPECIFIED -> return defaultSize
            //wrap_content
            MeasureSpec.AT_MOST -> max(defaultSize, size)
            //指定大小或者match_parent
            MeasureSpec.EXACTLY -> return size
        }
        return defaultSize
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawDial(canvas)
        drawScale(canvas)
        drawHand(canvas)
        drawAxis(canvas)
        postInvalidateDelayed(1000)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView)
        val typeArrayCount: Int = typedArray.indexCount
        for (index in 0..typeArrayCount) {
            val attrIndex: Int = typedArray.getIndex(index)
            if (attrIndex == R.styleable.ClockView_backgroundColor)
                bgColor = typedArray.getColor(attrIndex, Color.parseColor("#38c172"))
            if (attrIndex == R.styleable.ClockView_hoursHandColor)
                hourHandColor = typedArray.getColor(attrIndex, Color.BLACK)
            if (attrIndex == R.styleable.ClockView_minuteHandColor)
                minuteHandColor = typedArray.getColor(attrIndex, Color.BLACK)
            if (attrIndex == R.styleable.ClockView_secondHandColor)
                secondHandColor = typedArray.getColor(attrIndex, Color.BLACK)
            if (attrIndex == R.styleable.ClockView_dialColor)
                dialColor = typedArray.getColor(attrIndex, Color.BLACK)
            if (attrIndex == R.styleable.ClockView_scaleColor)
                scaleColor = typedArray.getColor(attrIndex, Color.parseColor("#0FCCCE"))
            if (attrIndex == R.styleable.ClockView_scaleRadios)
                scaleRadios = typedArray.getDimension(attrIndex, 8f)
            if (attrIndex == R.styleable.ClockView_scalePading)
                scalePadding = typedArray.getDimension(attrIndex, 60f)
            if (attrIndex == R.styleable.ClockView_anixRadio)
                axisRadios = typedArray.getDimension(attrIndex, 30f)
            if (attrIndex == R.styleable.ClockView_hourHandLength)
                hourHandLength = typedArray.getDimension(attrIndex, 300f)
            if (attrIndex == R.styleable.ClockView_hourHandWidth)
                hourHandWidth = typedArray.getDimension(attrIndex, 15f)
            if (attrIndex == R.styleable.ClockView_minuteHandLength)
                minuteHandLength = typedArray.getDimension(attrIndex, 400f)
            if (attrIndex == R.styleable.ClockView_minuteHandWidth)
                minuteHandWidth = typedArray.getDimension(attrIndex, 10f)
            if (attrIndex == R.styleable.ClockView_secondHandLength)
                secondHandLength = typedArray.getDimension(attrIndex, 500f)
            if (attrIndex == R.styleable.ClockView_secondHandWidth)
                secondHandWidth = typedArray.getDimension(attrIndex, 5f)
        }
        typedArray.recycle()
    }

    private fun drawBackground(canvas: Canvas) {
        bgPaint.color = bgColor
        canvas.drawRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), bgPaint)
    }

    private fun drawDial(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        val radios = min(width, height) / 2
        dialPaint.color = dialColor
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radios.toFloat(), dialPaint);
    }

    private fun drawScale(canvas: Canvas) {
        canvas.save()
        for (i in 0..12) {
            val radios: Float = if (i % 3 == 0) scaleRadios * 2 else scaleRadios
            val color: Int = if (i % 3 == 0) scaleColor else Color.parseColor("#ffffff")
            scalePaint.color = color
            canvas.drawCircle(
                min(width, height) / 2f,
                height / 2f - min(width, height) / 2f + scalePadding + scaleRadios,
                radios,
                scalePaint
            )
            canvas.rotate(360 / 12f, min(width, height) / 2f, max(width, height) / 2f)
        }
        canvas.restore()
    }

    private fun drawHand(canvas: Canvas) {
        // 获取当前时间：时分秒
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR]
        val minute = calendar[Calendar.MINUTE]
        val second = calendar[Calendar.SECOND]
        // 计算时分秒转过的角度
        val angleHour = (hour + minute.toFloat() / 60) * 360 / 12
        val angleMinute = (minute + second.toFloat() / 60) * 360 / 60
        val angleSecond = second.toFloat() * 360 / 60
        val radius = min(width, height) / 2
        val centerX: Float = min(width / 2f, height / 2f)
        val centerY: Float = max(width / 2f, height / 2f)
        //绘制时针
        canvas.save()
        canvas.rotate(angleHour, centerX, centerY)
        val rectHour = RectF(
            centerX - hourHandWidth / 2,
            centerY - hourHandLength,
            centerX + hourHandWidth / 2,
            centerY
        )
        hourHandPaint.color = hourHandColor
        hourHandPaint.style = Paint.Style.STROKE
        hourHandPaint.strokeWidth = hourHandWidth
        canvas.drawRect(rectHour, hourHandPaint)
        canvas.restore()
        //绘制分针
        canvas.save()
        canvas.rotate(angleMinute, centerX, centerY)
        val rectMinute = RectF(
            centerX - minuteHandWidth / 2,
            centerY - minuteHandLength,
            centerX + minuteHandWidth / 2,
            centerY
        )
        minuteHandPaint.color = minuteHandColor
        minuteHandPaint.style = Paint.Style.STROKE
        minuteHandPaint.strokeWidth = minuteHandWidth
        canvas.drawRect(rectMinute, minuteHandPaint)
        canvas.restore()
        //绘制秒针
        canvas.save()
        canvas.rotate(angleSecond, centerX, centerY)
        val rectSecond = RectF(
            centerX - secondHandWidth / 2,
            centerY - secondHandLength,
            centerX + secondHandWidth / 2,
            centerY + 100
        )
        secondHandPaint.color = secondHandColor
        secondHandPaint.style = Paint.Style.STROKE
        secondHandPaint.strokeWidth = secondHandWidth
        canvas.drawRect(rectSecond, secondHandPaint)
        canvas.restore()
    }

    private fun drawAxis(canvas: Canvas) {
        val centerX: Float = min(width / 2f, height / 2f)
        val centerY: Float = max(width / 2f, height / 2f)
        axisPaint.color = dialColor
        canvas.drawCircle(centerX, centerY, axisRadios, axisPaint)
        axisSubPaint.color = scaleColor
        canvas.drawCircle(centerX, centerY, axisRadios / 2, axisSubPaint)
    }

}
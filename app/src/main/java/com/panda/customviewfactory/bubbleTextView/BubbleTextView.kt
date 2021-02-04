package com.panda.customviewfactory.bubbleTextView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.panda.customviewfactory.R

/**
 * @ClassName BubbleTextView
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/1/27 下午5:52
 * @Version 1.0
 */
class BubbleTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //气泡画笔
    private val mBgPaint = Paint()
    private val mTrianglePaint = Paint()

    //文字画笔
    private val mTextPaint = Paint()
    private val mTextBound = Rect()
    private var mBgColor = 0
    private var mTextColor = 0
    private var mTextSize = 13
    private var mText: String? = "HELLO WORLD"
    private var arrowLocation: Int = 0;
    private var perpendicularLength: Int = 10
    private var bottomEdgeLength: Int = 16
    private var cornerRadios: Int = 12
    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.bubble_text_view)
        val typeArrayCount = typedArray.indexCount
        for (index in 0 until typeArrayCount) {
            val attrIndex = typedArray.getIndex(index)
            if (attrIndex == R.styleable.bubble_text_view_bgColor) mBgColor =
                typedArray.getColor(attrIndex, Color.parseColor("#FB5486"))
            if (attrIndex == R.styleable.bubble_text_view_textColor) mTextColor =
                typedArray.getColor(attrIndex, Color.parseColor("#FFFFFF"))
            if (attrIndex == R.styleable.bubble_text_view_textSize) mTextSize =
                typedArray.getDimensionPixelSize(attrIndex, 13)
            if (attrIndex == R.styleable.bubble_text_view_text) mText =
                typedArray.getString(attrIndex)
            if (attrIndex == R.styleable.bubble_text_view_arrowLocation) arrowLocation =
                typedArray.getInt(attrIndex, 0)
            if (attrIndex == R.styleable.bubble_text_view_perpendicularLength) perpendicularLength =
                typedArray.getDimensionPixelSize(attrIndex, 10)
            if (attrIndex == R.styleable.bubble_text_view_bottomEdgeLength) bottomEdgeLength =
                typedArray.getDimensionPixelSize(attrIndex, 16)
            if (attrIndex == R.styleable.bubble_text_view_cornerRadius) cornerRadios =
                typedArray.getDimensionPixelSize(attrIndex, 12)
        }
        typedArray.recycle()
        mTextPaint.color = mTextColor
        mTextPaint.textSize = mTextSize.toFloat()
        mTextPaint.typeface = Typeface.DEFAULT_BOLD
        mTextPaint.getTextBounds(mText, 0, mText!!.length, mTextBound)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width: Int
        var height: Int
        /**
         * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
         * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
         * UNSPECIFIED：表示子布局想要多大就多大，很少使用
         */
        width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            val textWidth = mTextBound.width().toFloat()
            (paddingLeft + textWidth + paddingRight).toInt()
        }
        if (arrowLocation == 1 || arrowLocation == 3) {
            width += perpendicularLength
        }
        height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            val textHeight = mTextBound.height().toFloat()
            (paddingTop + textHeight + paddingBottom).toInt()
        }
        if (arrowLocation == 0 || arrowLocation == 2) {
            height += perpendicularLength
        }
        setMeasuredDimension(width, height)
        Log.d("BubbleTextView", "onMeasure: width = $width + height = $height")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBg(canvas)
        drawText(canvas)
    }

    private fun drawBg(canvas: Canvas) {
        mBgPaint.color = mBgColor
        mTrianglePaint.color = mBgColor
        val path = Path()
        var left: Float = 0f
        var top: Float = perpendicularLength.toFloat()
        var right: Float = width.toFloat()
        var bottom: Float = height + perpendicularLength.toFloat()
        var pointTargetX: Float = (width shr 1).toFloat()
        var pointTargetY: Float = 0f
        var pointLeftX: Float = (width shr 1) - bottomEdgeLength / 2f
        var pointLeftY: Float = perpendicularLength.toFloat()
        var pointRightX: Float = (width shr 1) + bottomEdgeLength / 2f
        var pointRightY: Float = perpendicularLength.toFloat()
        when (arrowLocation) {
            0 -> {
                left = 0f
                top = perpendicularLength.toFloat()
                right = width.toFloat()
                bottom = height.toFloat()

                pointTargetX = (width shr 1).toFloat()
                pointTargetY = 0f
                pointLeftX = (width shr 1) - bottomEdgeLength / 2f
                pointLeftY = perpendicularLength.toFloat()
                pointRightX = (width shr 1) + bottomEdgeLength / 2f
                pointRightY = perpendicularLength.toFloat()
            }
            1 -> {
                left = 0f
                top = 0f
                right = width - perpendicularLength.toFloat()
                bottom = height.toFloat()

                pointTargetX = width.toFloat()
                pointTargetY = (height shr 1).toFloat()
                pointLeftX = (width - perpendicularLength).toFloat()
                pointLeftY = (height shr 1) + bottomEdgeLength / 2f
                pointRightX = (width - perpendicularLength).toFloat()
                pointRightY = (height shr 1) - bottomEdgeLength / 2f
            }
            2 -> {
                left = 0f
                top = 0f
                right = width.toFloat()
                bottom = height - perpendicularLength.toFloat()

                pointTargetX = (width shr 1).toFloat()
                pointTargetY = height.toFloat()
                pointLeftX = (width shr 1) - bottomEdgeLength / 2f
                pointLeftY = height - perpendicularLength.toFloat()
                pointRightX = (width shr 1) + bottomEdgeLength / 2f
                pointRightY = height - perpendicularLength.toFloat()
            }
            3 -> {
                left = perpendicularLength.toFloat()
                top = 0f
                right = width.toFloat()
                bottom = height.toFloat()

                pointTargetX = 0f
                pointTargetY = (height shr 1).toFloat()
                pointLeftX = perpendicularLength.toFloat()
                pointLeftY = (height shr 1) - bottomEdgeLength / 2f
                pointRightX = perpendicularLength.toFloat()
                pointRightY = (height shr 1) + bottomEdgeLength / 2f
            }

        }
        canvas.drawRoundRect(
            left,
            top,
            right,
            bottom,
            cornerRadios.toFloat(),
            cornerRadios.toFloat(),
            mBgPaint
        )
        path.close()
        path.moveTo(pointTargetX, pointTargetY)
        path.lineTo(pointLeftX, pointLeftY)
        path.lineTo(pointRightX, pointRightY)
        Log.d(
            "BubbleTextView",
            "drawBg: location = $arrowLocation "
                    + "rect =  $left $top $right $bottom  "
                    + "triangle = $pointTargetX $pointTargetX $pointLeftX $pointLeftY $pointRightX $pointRightY"
        )
        canvas.drawPath(path, mTrianglePaint)
    }

    private fun drawText(canvas: Canvas) {
        val fontMetrics = mTextPaint.fontMetrics
        val top = fontMetrics.top
        val bottom = fontMetrics.bottom
        var baseLineY = ((height) / 2 - top / 2 - bottom / 2).toInt()
        var x = paddingLeft.toFloat()
        if (arrowLocation == 0) {
            baseLineY = ((height + perpendicularLength) / 2 - top / 2 - bottom / 2).toInt()
        }
        if (arrowLocation == 2) {
            baseLineY = ((height - perpendicularLength) / 2 - top / 2 - bottom / 2).toInt()
        }
        if (arrowLocation == 1) {
            x = paddingLeft.toFloat()
        }
        if (arrowLocation == 3) {
            x = paddingLeft.toFloat() + perpendicularLength
        }
        canvas.drawText(mText!!, x, baseLineY.toFloat(), mTextPaint)
    }

    init {
        init(context, attrs)
    }
}
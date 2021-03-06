package com.example.supercircleview.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.supercircleview.R


class SuperCircleView : View {
    lateinit var valueAnimator :ValueAnimator
    var mCircleRadius :Int =0
    var mRingWidth : Int =0
    var mRingColor : Int = 0
    var mValue : Int =0
    var mMaxValue : Int =0
    var mRingColorSelect : Int =0
    var mMaxCircleColor : Int =0
    var mMinCircleColor : Int =0
    lateinit var mRectF : RectF
    lateinit var mPaint :Paint

    val color =IntArray(3)

    var mViewCenterX :Int =0
    var mViewCenterY :Int =0
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        initAttrs(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context ,attrs, defStyle){
        initAttrs(context ,attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet){
        val array = context.obtainStyledAttributes(attrs, R.styleable.SuperCircleView)
        mCircleRadius = array.getInt(R.styleable.SuperCircleView_min_circle_radius, 200)
        mRingWidth = array.getInt(R.styleable.SuperCircleView_ring_width,100)
        Log.d(TAG, "initAttrs: "+mRingWidth)
        mRingColor = array.getInt(R.styleable.SuperCircleView_ring_color,ContextCompat.getColor(context,R.color.green))
        mValue = array.getInt(R.styleable.SuperCircleView_value,0)
        mMaxValue =array.getInt(R.styleable.SuperCircleView_maxValue,100)
        mRingColorSelect =array.getInt(R.styleable.SuperCircleView_ring_color_select,0)
        mMinCircleColor =array.getInt(R.styleable.SuperCircleView_min_circle_color,
                ContextCompat.getColor(context,R.color.blue_dark2))
        array.recycle()

        //抗锯齿
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.isAntiAlias=true

        color[0] = Color.parseColor("#FFD300")
        color[1] = Color.parseColor("#FF0084")
        color[2] = Color.parseColor("#16FF00")
    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val mViewWidth = measuredWidth
        val mViewHeight = measuredHeight

        mViewCenterX =mViewWidth/2
        mViewCenterY =mViewHeight/2
        mRectF= RectF((mViewCenterX-mCircleRadius-mRingWidth/2).toFloat(), (mViewCenterY-mCircleRadius-mRingWidth/2).toFloat(),
                (mViewCenterX+mCircleRadius+mRingWidth/2).toFloat(), (mViewCenterY+mCircleRadius+mRingWidth/2).toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //画小圆
        drawMinCircle(canvas)

        //画默认圆环
        drawNormalRing(canvas)

        // 画彩色圆环
      //  drawColorRing(canvas)
    }

    private fun drawMinCircle(canvas: Canvas?){
        val mCirclePaint=Paint(mPaint)
        mCirclePaint.color = mMinCircleColor
        canvas!!.drawCircle(mViewCenterX.toFloat(), mViewCenterY.toFloat(), mCircleRadius.toFloat(), mCirclePaint)
    }

    private fun drawNormalRing(canvas: Canvas?){
       val mNormalPaint=Paint(mPaint)
        mNormalPaint.style=Paint.Style.STROKE
        mNormalPaint.color=mRingColor
        mNormalPaint.strokeWidth= mRingWidth.toFloat()

        canvas!!.drawArc(mRectF, 0F, 360F,false,mNormalPaint)
    }

    private fun drawColorRing(canvas: Canvas?){
        val mColorRingPaint= Paint(mPaint)
        mColorRingPaint.style=Paint.Style.STROKE
        mColorRingPaint.strokeWidth=mRingWidth.toFloat()
        mColorRingPaint.shader = SweepGradient(mViewCenterX.toFloat(), mViewCenterY.toFloat(),color,null)
        canvas!!.rotate((-90).toFloat(),mViewCenterX.toFloat(),mViewCenterY.toFloat())
        canvas.drawArc(mRectF, 360F, mRingColorSelect.toFloat(), false, mPaint)
        mPaint.shader = null
    }

    /**
     * 设置当前值
     *
     * @param value
     */
    fun setValue(value: Int, textView: TextView) {
        var v = value
        if (v > mMaxValue) {
            v = mMaxValue
        }
        val start = 0
        val end = v
        startAnimator(start, end, 2000, textView)
    }

    private fun startAnimator(start: Int, end: Int, animTime: Long, textView: TextView) {
        valueAnimator = ValueAnimator.ofInt(start, end)
        valueAnimator.duration = animTime
        valueAnimator.addUpdateListener { animation ->
            val i = Integer.valueOf(animation.animatedValue.toString())
            textView.text = """$i"""
            //每个单位长度占多少度
            mRingColorSelect = (360 * (i / 100f)).toInt()
            invalidate()
        }
        valueAnimator.start()
    }


}
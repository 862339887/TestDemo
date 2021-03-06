package com.example.supercircleview.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.supercircleview.MainActivity
import com.example.supercircleview.R
import java.time.format.DecimalStyle
import java.util.logging.Handler
import kotlin.math.min


class MyWifiView : View {
    lateinit var mWifiPaint: Paint
    private val startAngle =-135
    private var sweepAngle = 90
    private lateinit var mRect :RectF
    private var mWifiDimension =0
    private var radius =0
    private var shouldDrawArcNums = 1
    private val totalArcNums = 4

    constructor(context:Context):super(context)
    constructor(context: Context, attrs:AttributeSet):super(context,attrs){
        initPaint()
    }
    constructor(context: Context,attrs: AttributeSet,defStyle: Int):super(context,attrs,defStyle){
        initPaint()
    }

    private fun initPaint(){
        mWifiPaint= Paint(Paint.ANTI_ALIAS_FLAG)
        mWifiPaint.strokeWidth=6F
        mWifiPaint.color = ContextCompat.getColor(context,R.color.black)
        mWifiPaint.style=Paint.Style.FILL
        handler.postDelayed(object :Runnable{
            override fun run() {
                invalidate()
                handler.postDelayed(this,500)
            }
        },500)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWifiDimension= min(measuredWidth,measuredHeight)
        radius =mWifiDimension/2/totalArcNums
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shouldDrawArcNums++
        if(shouldDrawArcNums>totalArcNums)shouldDrawArcNums=1
        canvas.save()
        for(i in 0 until totalArcNums){
            if (i >= totalArcNums - shouldDrawArcNums){
                val offset= radius*i
                mRect=RectF(offset.toFloat(), offset.toFloat(),
                        (mWifiDimension-offset).toFloat(), (mWifiDimension-offset).toFloat())
                if(i<totalArcNums-1){
                    mWifiPaint.style=Paint.Style.FILL
                    canvas.drawArc(mRect, startAngle.toFloat(), sweepAngle.toFloat(),true,mWifiPaint)
                }else{
                    mWifiPaint.style=Paint.Style.STROKE
                    canvas.drawArc(mRect,startAngle.toFloat(),sweepAngle.toFloat(),false,mWifiPaint)
                }
            }
        }
        canvas.restore()
    }
}
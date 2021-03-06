package com.example.supercircleview.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by PeiHuan on 2017/6/24.
 *
 *
 * WIFI控件
 */
class WIFIView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var paint: Paint? = null

    /**
     * 初始化准备
     */
    private fun init() {
        paint = Paint()
        //画笔颜色
        paint!!.color = Color.BLACK
        //画笔粗细
        paint!!.strokeWidth = 6f
        //抗锯齿
        paint!!.isAntiAlias = true
        handler.postDelayed(object : Runnable {
            override fun run() {
                invalidate()
                handler.postDelayed(this, 500)
            }
        }, 500)
    }

    private var handler = object :Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            //
        }
    }
    /**WIFI控件宽高较小一边的长度 */
    private var wifiLength = 0
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        wifiLength = Math.min(w, h)
    }

    /**
     * 开始角度
     */
    private val startAngle = -135f

    /**
     * 扇形或者弧的旋转角度
     */
    private val sweepAngle = 90f

    /**
     * 信号大小，默认4格
     */
    private val signalSize = 4

    /**每次应该绘制的信号个数 */
    private var shouldExistSignalSize = 0f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shouldExistSignalSize++
        if (shouldExistSignalSize > 4) {
            shouldExistSignalSize = 1f
        }
        canvas.save()
        //计算最小的扇形信号所在的圆的半径
        val signalRadius = wifiLength / 2 / signalSize.toFloat()
        //向下平移画布,保证绘制的图形能够完全显示
        canvas.translate(0f, signalRadius)
        for (i in 0 until signalSize) {
            if (i >= signalSize - shouldExistSignalSize) {
                //定义每个信号所在圆的半径
                val radius = signalRadius * i
                val rectf = RectF(radius, radius, wifiLength - radius, wifiLength - radius)
                if (i < signalSize - 1) {
                    paint!!.style = Paint.Style.STROKE
                    canvas.drawArc(rectf, startAngle, sweepAngle, false, paint!!)
                } else {
                    paint!!.style = Paint.Style.FILL
                    canvas.drawArc(rectf, startAngle, sweepAngle, true, paint!!)
                }
            }
        }
        canvas.restore()
    }

    init {
        init()
    }
}
package com.example.viewtreeobservertest

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val observer = layout.viewTreeObserver

    val listener={
        oldFocus: View?, newFocus: View?->
        remove()
    }


    private fun remove() {

        observer.removeOnGlobalFocusChangeListener(listener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val observer = layout.viewTreeObserver
        val observer2 = et_1.viewTreeObserver


        observer.addOnGlobalFocusChangeListener {
            oldFocus: View?, newFocus: View?->
            observer.removeOnGlobalFocusChangeListener()
        }
        }
        observer.addOnGlobalFocusChangeListener(object : ViewTreeObserver.OnGlobalFocusChangeListener {
            override fun onGlobalFocusChanged(oldFocus: View?, newFocus: View?) {
                Log.e("MainActivity", "initObserver: Focus changed ${observer.isAlive}")
                Log.e("requestFocus", "${observer.isAlive}")

                if (observer.isAlive) {
                    observer.removeOnGlobalFocusChangeListener(this)
                }


                Handler().postDelayed({
                    Log.e("requestFocus", "1-${observer2?.isAlive}")
                    btn?.requestFocus()
                }, 3000)
            }
        })
        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (observer.isAlive) {
                    observer.removeOnGlobalLayoutListener(this)
                }
                val width = layout.measuredWidth
                val height = layout.measuredHeight
                Log.e("MainActivity", "initObserver: width: $width\theight:$height")
            }

        })

        observer.addOnScrollChangedListener {

        }

        observer.addOnTouchModeChangeListener {
            print("it:$it")
        }


        Handler().postDelayed({
            Log.e("requestFocus", "1-${observer?.isAlive}")
            btn?.requestFocus()
        }, 100)

    }

}

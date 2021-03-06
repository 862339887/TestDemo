package com.example.registerlistenertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var listener: (String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnListener {
            textView.text = it
        }

//        Handler(Looper.getMainLooper()).postDelayed(Runnable {
//            listener.invoke("触发成功")
//        }, 1000)

    }

    private fun setOnListener(str: (String) -> Unit){
        listener = str
    }
}
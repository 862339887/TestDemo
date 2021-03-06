package com.example.supercircleview


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1=findViewById<Button>(R.id.button1)
        val button2=findViewById<Button>(R.id.button2)
        val textView=findViewById<TextView>(R.id.textView)

        val handler=Handler(Looper.getMainLooper())
        Handler(Looper.getMainLooper()).postDelayed(object :Runnable{
            override fun run() {
                textView.invalidate()
                handler.postDelayed(this,500)
            }
        },500)

    }
}
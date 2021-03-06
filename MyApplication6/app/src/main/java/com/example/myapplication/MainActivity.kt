package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            val imageView=ImageView(this)
            imageView.layoutParams=lp
            imageView.setImageResource(R.drawable.ic_launcher_background)

            test_ll.addView(imageView)
        }

//        lp.gravity=Gravity.CENTER
//        lp.setMargins(20,20,20,20)
//        val textView=TextView(this)
//        textView.layoutParams=lp
//        textView.setTextSize(22.toFloat())
//        textView.text="wocaonima"
//        test_ll.addView(textView)
//
//        val button=Button(this)
//        button.text="button"
//        button.gravity=Gravity.LEFT
//        test_ll.addView(button)


//        var lp1=test_tv.layoutParams as LinearLayout.LayoutParams
//
//        lp1.height=400
//        lp1.width=400
//        lp1.gravity=Gravity.CENTER
//        test_tv.gravity=Gravity.CENTER
//        test_tv.layoutParams=lp1
    }
}
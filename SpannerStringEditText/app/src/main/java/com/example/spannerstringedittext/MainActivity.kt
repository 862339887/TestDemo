package com.example.spannerstringedittext

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ImageSpan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spanString=SpannableString(" ")
      //  val backgroundSpan=BackgroundColorSpan(R.color.purple_200)
       // spanString.setSpan(backgroundSpan,0,3,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        val d=resources.getDrawable(R.drawable.ic_launcher_background)
        d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight);
        val imageSpan=ImageSpan(d,ImageSpan.ALIGN_BASELINE)
        spanString.setSpan(imageSpan,0,1,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView_tv.append(spanString)
    }
}
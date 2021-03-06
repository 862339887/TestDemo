package com.example.spantest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        val spannableString =  SpannableStringBuilder()
        spannableString.append("暗影IV已经开始暴走了");
        //图片
        val imageSpan = ImageSpan(this, R.mipmap.ic_launcher);
        spannableString.setSpan(imageSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        val clickableSpan = object :ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        //文字颜色
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
//        spannableString.setSpan(colorSpan,5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        //文字背景颜色
//        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
//        spannableString.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        TextView textView = (TextView)findViewById(R.id.mode10);
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance();
    }
}
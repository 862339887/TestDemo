package com.example.cursorproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            initView()
        }
    }
    private fun initView(){
        aaaaa.selectionStart=2
//        Log.e("selectionStart1", "selectionStart="+aaaaa.selectionStart )
//        Log.e("selectionEnd", "selectionEnd="+aaaaa.selectionEnd )

    }
}
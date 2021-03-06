package com.example.fragmenttest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        button1.setOnClickListener {
            startActivity(Intent(this, StaticActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, ActiveActivity::class.java))
        }
    }

}
package com.example.viewpager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val NUM_PAGES = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun onBackPressed() {
        Toast.makeText(this,"fdfafdfafd",Toast.LENGTH_LONG).show()
        if(viewpager.currentItem==0){
            super.onBackPressed()
        }else{
            viewpager.currentItem--
        }
    }

    private fun initView() {
        val fragmentManager = supportFragmentManager
        val adapter = ViewPagerAdapter(fragmentManager, NUM_PAGES)
        viewpager.adapter = adapter
    }
}
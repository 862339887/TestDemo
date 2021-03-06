package com.example.slidemenuproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val adapter by lazy {  1 }
    companion object{
        private val titleList= listOf("Tab1","Tab2","Tab3","Tab4")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        Thread{
            println("我草你妈")
        }.start()



        Thread(object:Runnable{
            override fun run() {
                println("caonima ")
            }
        })
    }

    private fun initView(){
        val fragmentManager=supportFragmentManager
        val viewpagerAdapter=SimpleViewPagerAdapter(fragmentManager,titleList)
        viewPager.adapter=viewpagerAdapter
        fragment_tab.setupWithViewPager(viewPager)
        fragment_tab.tabMode=TabLayout.MODE_FIXED
    }
}
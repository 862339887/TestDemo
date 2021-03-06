package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_active.*

class ActiveActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active)
        initView()
    }

    private fun initView(){
        button_replace1.setOnClickListener {
            val fragmentManager=supportFragmentManager
            val transaction=fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_right,LeftFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        button_replace2.setOnClickListener {
            val fragmentManager=supportFragmentManager
            val transaction=fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_right,RightFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
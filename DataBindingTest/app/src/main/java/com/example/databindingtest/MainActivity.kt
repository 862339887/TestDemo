package com.example.databindingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingtest.databinding.UserBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding: UserBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user1 = User("zhouxin", "123456")
        activityBinding.userInfo = user1
        change_data.setOnClickListener {
            val intent= Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
        initView()
    }

    private fun initView(){

    }
}
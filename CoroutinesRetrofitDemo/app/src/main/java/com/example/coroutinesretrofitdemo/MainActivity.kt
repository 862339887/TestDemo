package com.example.coroutinesretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(RequestViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        bindLiveData()
    }

    private fun initView(){
        send_request.setOnClickListener {
            viewModel.requestMsg()
        }
    }
    private fun bindLiveData(){
        viewModel.data.observe(this, Observer {
            display_msg.text=it.message
//            Toast.makeText(this,"草你妈",Toast.LENGTH_LONG).show()
        })
    }
}
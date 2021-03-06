package com.example.livedatatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG="MainActivity"
    }
    private var nameViewModel:NameViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initView()
    }

    private fun initView(){
        button_name.setOnClickListener {
            nameViewModel!!.getCurrentName().value="Jane"
            val arr = arrayOf(1,3,5,7,9)
// 过滤掉数组中元素小于2的元素，取其第一个打印。这里的it就表示每一个元素。
            println(arr.filter { it < 5 }.component1())
        }

        button_namelist.setOnClickListener {
            val list1= mutableListOf<String>()
            for(i in 0..10 step 2){
                list1.add(i.toString())
            }
            val list= listOf("1","2","3","4")
            nameViewModel!!.getCurrentNameList().value= list
        }
    }

    private fun initViewModel(){

        nameViewModel= ViewModelProvider(this).get(NameViewModel::class.java)
        nameViewModel!!.getCurrentName().observe(this, object :Observer<String>{
            override fun onChanged(t: String) {
                textview_tv.text=t
            }
        })

        nameViewModel!!.getCurrentNameList().observe(this,Observer{
            for(name in it){
                Log.e(TAG, "name is $name")
            }
        })
    }
}


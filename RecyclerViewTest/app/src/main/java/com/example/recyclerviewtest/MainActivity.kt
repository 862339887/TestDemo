package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val dataList = arrayListOf(
        "我的关注", "通知开关", "我的徽章", "意见反馈", "我要投稿",
        "我的关注", "通知开关", "我的徽章", "意见反馈", "我要投稿",
        "我的关注", "通知开关", "我的徽章", "意见反馈", "我要投稿"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var var_a: Int = 10

//推导出类型
        var var_b = 5

//没有初始化的时候，必须声明类型

        val layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerViewAdapter(dataList, this)

        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }
}
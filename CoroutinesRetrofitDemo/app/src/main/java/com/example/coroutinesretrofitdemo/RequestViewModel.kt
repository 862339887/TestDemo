package com.example.coroutinesretrofitdemo

import android.service.voice.AlwaysOnHotwordDetector
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Math.log
import kotlin.concurrent.thread
import kotlin.math.ln

class RequestViewModel: ViewModel() {
    var data=MutableLiveData<DataBean>()
    private lateinit var result:DataBean
    private val mPostRequest: PostRequestInterface by lazy {
        //步骤4:创建Retrofit对象
        ln(2.0)
        GlobalScope.launch {

        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cloudapi.bytedance.net/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(CoroutineCallAdapterFactory::class.java)//设置使用Gson解析(记得加入依赖)
            .build()
        // 步骤5:创建 网络请求接口 的实例
        // 步骤5:创建 网络请求接口 的实例
        retrofit.create(PostRequestInterface::class.java)
    }
    fun requestMsg(){
        CoroutineScope(Dispatchers.Main).launch {
             result=getData()
            data.value=result
        }
    }

    private suspend fun getData()=withContext(Dispatchers.IO){
        mPostRequest.getMessage(RequestBean("12354"))
    }
}

data class RequestBean(val payload: String)
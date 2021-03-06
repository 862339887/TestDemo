package com.example.coroutinespractice

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.example.coroutinespractice.ext.MyViewModel
import com.example.coroutinespractice.ext.launch
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext
interface MainScoped {
    companion object {
        internal val scopeMap = IdentityHashMap<MainScoped, MainScope>()
    }
    val mainScope: CoroutineScope
        get() = scopeMap[this as Activity]!!
}
class MainActivity : AppCompatActivity(),CoroutineScope by MainScope() {
    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    override fun onDestroy() {
        super.onDestroy()
        GlobalScope.cancel()
    }
    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        CoroutineScope(Dispatchers.Main).launch{
//            val s=ViewModelProvider(this@MainActivity).get(MyViewModel::class.java)
//           val job= s.launch {
//                println(Thread.currentThread().name)
//            }
//        }

        val job=GlobalScope.launch(start = CoroutineStart.LAZY){
            println(Thread.currentThread().name)
            delay(1000)
            println(Thread.currentThread().name)
            println("My job is ${coroutineContext[Job]}")
        }.start()
    }
}
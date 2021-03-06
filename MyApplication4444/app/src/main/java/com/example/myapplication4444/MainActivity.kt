package com.example.myapplication4444

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addLog("11111")
    }
    fun addLog(power_level: String) {
        Log.d("AppendPower", "In addLog method")
        val externalFile = getExternalCacheDir().toString()
        val internalFile=filesDir.toString()





    }
}
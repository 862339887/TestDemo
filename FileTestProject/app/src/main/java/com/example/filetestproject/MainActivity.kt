package com.example.filetestproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFile()
    }

    private fun createFile(){
      //  val file1=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file4=externalCacheDir
        val file1 =getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file2 =getExternalFilesDir(Environment.DIRECTORY_ALARMS)
        val file3 =getExternalFilesDirs(null)

        // val file3=getExternalFilesDirs(null)

    }
}
package com.example.textwatcherpractice.exit

import android.app.Activity
import android.app.Application
import android.widget.Toast

fun Activity.toastUtil(msg:String){
    Toast.makeText(applicationContext,msg,Toast.LENGTH_LONG).show()
}
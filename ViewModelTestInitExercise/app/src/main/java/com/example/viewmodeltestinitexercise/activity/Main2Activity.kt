package com.example.viewmodeltestinitexercise.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.viewmodeltestinitexercise.AnimalViewModel
import com.example.viewmodeltestinitexercise.R
import com.example.viewmodeltestinitexercise.databinding.ActivityMainBinding
import com.example.viewmodeltestinitexercise.model.Animal

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dog=Animal("dog",0)
        dataBinding.vm= AnimalViewModel(dog)
    }
}
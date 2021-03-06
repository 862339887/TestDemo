package com.example.viewmodeltestinitexercise.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewmodeltestinitexercise.R
import com.example.viewmodeltestinitexercise.model.Animal
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val dog=Animal("dog",0)
        shout_data.text="${dog.name}叫了${dog.shoutCount}次"
        button.setOnClickListener {
            dog.shoutCount=dog.shoutCount!!+1
            shout_data.text="${dog.name}叫了${dog.shoutCount}次"
        }

    }
}
package com.example.startactivitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button1 =findViewById<Button>(R.id.button1)
        when(intent.getStringExtra("1")){
            "1"->Toast.makeText(this, "按钮1", Toast.LENGTH_SHORT).show()
            else->Toast.makeText(this, "按钮2", Toast.LENGTH_SHORT).show()

        }
        button1.setOnClickListener {
            val data=intent.putExtra("change1","我是来自于关闭1")
            this.setResult(1,data)
            finish()
        }

        val button2=findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val  data =intent.putExtra("change2","我是来自于关闭2")
            this.setResult(2,data)
        }

    }
}
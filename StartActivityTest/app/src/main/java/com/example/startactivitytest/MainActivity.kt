package com.example.startactivitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 =findViewById<Button>(R.id.button1)

        val button2=findViewById<Button>(R.id.button2)

        button1.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java)
            intent.putExtra("1","1")
            startActivityForResult(intent,1)
        }

        button2.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java)
            intent.putExtra("1","2")
            startActivityForResult(intent,2)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1-> Toast.makeText(this, "来自于按钮1", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "来自于按钮2", Toast.LENGTH_SHORT).show()
        }
    }
}
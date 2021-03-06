package com.example.toolbartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "点击了导航按钮", Toast.LENGTH_SHORT).show()
        }
        toolbar.inflateMenu(R.menu.toorbar_menu)

        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.ic_action1 -> {
                    Toast.makeText(this, "点击了search按钮", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.ic_action2 -> {
                    Toast.makeText(this, "点击了bell按钮", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.ic_action3->{
                    Toast.makeText(this, "点击了设置按钮", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.ic_action4->{
                    Toast.makeText(this, "点击了关于按钮", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toorbar_menu,menu)
        return true
    }
}
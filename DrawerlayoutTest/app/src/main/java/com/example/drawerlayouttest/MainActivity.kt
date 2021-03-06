package com.example.drawerlayouttest

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_test_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imageview_test_layout)

        window.setDecorFitsSystemWindows(false)
      val control= window.insetsController
        if(control!=null){
            control.hide(WindowInsets.Type.navigationBars())
            control.hide(WindowInsets.Type.statusBars())
            control.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


        }
            //  window.statusBarColor = Color.TRANSPARENT  transient
        //window.navigationBarColor = Color.TRANSPARENT
//        WindowManager.LayoutParams.setFitInsetsTypes()
//
//        if (Build.VERSION.SDK_INT >= 21) {
//
//
//        }
//        val actionBar = supportActionBar
//        actionBar!!.hide()
////        toolbar.isVisible=false
////        window.insetsController?.also {
////            it.hide(WindowInsets.Type.statusBars())
////            it.hide(WindowInsets.Type.navigationBars())
////        }


      //  initView()



        //setImmerseMode()
    }


    private fun setImmerseMode(){
//        val decorView = window.decorView
//        val controller = ViewCompat.getWindowInsetsController()
//        controller?.hide(WindowInsetsCompat.Type.statusBars())
//        controller?.hide(WindowInsetsCompat.Type.systemBars())
//        controller?.hide(WindowInsetsCompat.Type.navigationBars())
      //  window.setDecorFitsSystemWindows(false)
        Build.
        window.insetsController?.also {
            it.hide(WindowInsets.Type.statusBars())
            it.hide(WindowInsets.Type.navigationBars())
        }

        val decorView: View = getWindow().getDecorView()
        val option: Int = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        decorView.systemUiVisibility = option
        window.statusBarColor = Color.TRANSPARENT
        //decorView.setSystemUiVisibility(SYSTEM_UI_FLAG_HIDE_NAVIGATION)



    }


    private fun initView(){
//        open_drawer_icon.setOnClickListener{
//            drawer_layout.openDrawer(GravityCompat.START)
//        }


        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            Log.d("MainActivty", "initView: "+ "11111111")
            Toast.makeText(this, "点击了导航按钮", Toast.LENGTH_LONG).show()
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
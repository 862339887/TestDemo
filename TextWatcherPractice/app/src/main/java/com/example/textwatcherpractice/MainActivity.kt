package com.example.textwatcherpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.textwatcherpractice.exit.toastUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG="MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        edit_text.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s!!.append("hello")
                Log.e(TAG, "afterTextChanged: "+"s=${s}" )
//                toastUtil("afterTextChanged: "+"s=${s}")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                toastUtil("s=${s},start=${start},count=${count},after=${after}")
                Log.e(TAG, "beforeTextChanged: "+"s=${s},start=${start},count=${count},after=${after}" )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                toastUtil("onTextChanged: "+"s=${s},start=${start},before=${before},count=${count}")
                Log.e(TAG, "onTextChanged: "+"s=${s},start=${start},before=${before},count=${count}" )
            }
        })
    }
}
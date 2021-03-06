package com.example.commentdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.correct_comment_layout.view.*

class CorrectCommentDisplayView @kotlin.jvm.JvmOverloads constructor(ctx0: Context, attributeSet: AttributeSet?, defStyleAttr: Int = 0)
    : RelativeLayout(ctx0, attributeSet, defStyleAttr) {
    var mListener: OnCommentClickListener? = null


    init {
        LayoutInflater.from(context).inflate(R.layout.correct_comment_layout,this)
        initView()
    }

    fun setListener(listener: OnCommentClickListener? = null) {
        this.mListener= listener
    }

    

    private fun initView() {
    //    onAddImageCallback?.invoke(123)
        Log.e("MainActivity", "onSendComment: "+11111111111 )
        comment_all_send.setOnClickListener{
            mListener?.onSendComment()
        }

    }
}

interface OnCommentClickListener {
    fun onAddImageClick() {}
    fun onSendComment()
    fun deleteComment(){}

}


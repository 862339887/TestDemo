package com.example.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content_layout.view.*

class ViewPagerFragment(var position:Int?): Fragment (){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.content_layout,container,false)
        view.content_view.text=position.toString()
        return view
    }
}
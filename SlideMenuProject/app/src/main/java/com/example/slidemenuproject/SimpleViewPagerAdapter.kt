package com.example.slidemenuproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleViewPagerAdapter(fm:FragmentManager, private val titleList:List<String>?):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return SimplePageFragment(position+1)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList != null) {
            titleList[position]
        }else{
            null
        }
    }


}
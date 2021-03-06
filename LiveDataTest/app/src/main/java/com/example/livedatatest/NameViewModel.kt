package com.example.livedatatest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    private var mutableName: MutableLiveData<String>? = null
    private var mutableNameList: MutableLiveData<List<String>>? = null

    fun getCurrentName(): MutableLiveData<String> {
        if (mutableName == null) {
            mutableName = MutableLiveData()
        }
        return mutableName!!
    }

    fun getCurrentNameList():MutableLiveData<List<String>> {
        if (mutableNameList == null) {
            mutableNameList = MutableLiveData()
        }
        return mutableNameList!!
    }
}
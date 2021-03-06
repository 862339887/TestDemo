package com.example.lifecycletest

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
class MyObserver : DefaultLifecycleObserver {
    
    companion object{
        private const val TAG="MyObserver"
    }
    
    override fun onStart(owner: LifecycleOwner) {
        Log.e(TAG, "onStart: ")

        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }
    
    override fun onCreate(owner: LifecycleOwner) {
        Log.e(TAG, "onCreate: ")
        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.e(TAG, "onResume: ")
        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }
    
    override fun onPause(owner: LifecycleOwner) {
        Log.e(TAG, "onPause: ")
        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.e(TAG, "onStop: ")
        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }
    
    override fun onDestroy(owner: LifecycleOwner) {
        Log.e(TAG, "onDestroy: ")
        Log.d(TAG,"当前生命周期状态="+owner.lifecycle.currentState.name)
    }

}
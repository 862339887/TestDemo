package com.example.rxjavatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity.kt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserver()


    }

    private fun initObserver() {
//        val observable: Observable<Int> = Observable.create { emitter ->
//            emitter.onNext(1)
//            emitter.onNext(2)
//            emitter.onNext(3)
//            emitter.onComplete()
//        }


        val observer: Observer<Long> = object : Observer<Long> {
            var mDisposable: Disposable? =null
            override fun onComplete() {
                Log.e(TAG, "对complete事件作出响应")
            }

            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "开始subscribe连接")
                mDisposable=d
            }

//            override fun onNext(t: Int) {
//                if(t==2){
//                    Log.e(TAG, "截断")
//
//                    mDisposable!!.dispose()
//
//                }else{
//                    Log.e(TAG, "执行next事件$t")
//                }
//            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "发生错误")
            }

            override fun onNext(t: Long) {
                Log.e(TAG, "执行next事件$t")
            }

        }

        Observable.timer(2,TimeUnit.SECONDS).subscribe(observer)

//        observable.subscribe(observer)
    }
}
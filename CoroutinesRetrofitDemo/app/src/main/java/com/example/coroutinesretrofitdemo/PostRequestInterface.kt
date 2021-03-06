package com.example.coroutinesretrofitdemo

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface PostRequestInterface {
//    @FormUrlEncoded
    @POST("faas/services/ttknj2/invoke/say")
//    fun getMessage(@Body map:Map<String,String> ):Deferred<DataBean>
    suspend fun getMessage(@Body request:RequestBean ):DataBean

//    fun getMessage(@Field("payload") key:String,@Field("value") value:String):Deferred<DataBean>
//    fun getMessage(@FieldMap params:Map<String,String>):Deferred<DataBean>
}
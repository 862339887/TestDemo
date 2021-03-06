package com.example.retrofittest

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface GetRequest_Interface {
    // 注解里传入 网络请求 的部分URL地址
    @get:GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    val call: Deferred<Translation?>?
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
}
package com.example.livedatatest

fun main() {
    var a= ArrayList<String>()
    a.add("1111")
    val b=a
    a.add("222")
    println(b)

    println(a)
}
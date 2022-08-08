package com.example.helloworld

//data关键字可以让这个类变成数据类，从而自动实现equals(), hashCode(),toString()等固定操作
data class Cellphone(val brand:String, val price: Double)
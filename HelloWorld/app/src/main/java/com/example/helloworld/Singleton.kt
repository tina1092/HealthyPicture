package com.example.helloworld
//object 代表Singleton这个class只会单独有一个，不像Student你可以call s1,s2,s3..但是Singleton只有一个s1
object Singleton {
    //也不需要私有化构造函数了
    fun singletonTest(){
        println("singleton test is called.")
    }
}
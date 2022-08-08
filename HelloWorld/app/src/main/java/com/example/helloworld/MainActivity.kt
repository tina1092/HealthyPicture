package com.example.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("data","onCreate execute")//tag:用于过滤有用的信息，只针对mainActivity这个界面的log；msg是打印的具体内容
    }
}
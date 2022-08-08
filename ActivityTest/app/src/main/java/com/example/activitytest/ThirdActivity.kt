package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_layout)
        Log.d("singleTask","Third Task id is $taskId")
        val button3 : Button = findViewById(R.id.button3)//findviewbyid获取layout文件中定义的元素
        button3.setOnClickListener(){
            ActivityCollector.finishAll()
        }
    }
}
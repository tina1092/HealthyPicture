package com.example.uicustomviews

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}
class TitleLayout(context: Context,attrs: AttributeSet): LinearLayout(context,attrs){
    init{
        LayoutInflater.from(context).inflate(R.layout.title,this)
        val titleBack: Button = findViewById(R.id.titleBack)
        titleBack.setOnClickListener{
            //调用当前activity（context类型），并转换成Activity类型
            val activity = context as Activity
            //使用 finish()摧毁当前 页面
            activity.finish()
        }
        val titleEdit:Button = findViewById(R.id.titleEdit)
        titleEdit.setOnClickListener{
            Toast.makeText(context,"you clicked edit button", Toast.LENGTH_SHORT).show()
        }
    }
}
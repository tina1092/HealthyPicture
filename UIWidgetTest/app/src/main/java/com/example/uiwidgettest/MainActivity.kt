package com.example.uiwidgettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.button)//findviewbyid获取layout文件中定义的元素
        button.setOnClickListener(){
            //写button对应的逻辑
            //在这里写的逻辑是把文本框输入的文字提取出来，再用toast打出在提示文本里
            val editText: TextView = findViewById(R.id.editText)
            val inputText = editText.text.toString()
            Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show()

            //通过点击按钮将xml原本的图片更改为名为img_2的图片
            val imageView: ImageView = findViewById(R.id.imageView)
            imageView.setImageResource(R.drawable.img_2)

            //点击按钮让进度条消失，再点击一下就又出现
            val progressBar: ProgressBar = findViewById(R.id.progressBar)
            if(progressBar.visibility == View.VISIBLE){
                progressBar.visibility = View.GONE
            }else{
                progressBar.visibility = View.VISIBLE
            }
            //点击按钮让进度条前进10
            val progressBar2: ProgressBar = findViewById(R.id.progressBar)
            progressBar2.progress += 10

            AlertDialog.Builder(this).apply{
                setTitle("this is alert dialog")
                setMessage("someth important")
                setCancelable(false)//是否可以用back键退出
                setPositiveButton("ok"){dialog, which->}//写逻辑
                setNegativeButton("concel"){dialog,which->}//写逻辑
                show()
            }

        }

    }
}
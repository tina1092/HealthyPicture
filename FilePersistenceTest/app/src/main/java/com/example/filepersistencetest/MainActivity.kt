package com.example.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputText = load()
        if(inputText.isNotEmpty()){
            val editText:EditText = findViewById(R.id.edittext)
            //讲提取出来的文本（inputText）放入编辑文件
            editText.setText(inputText)
            //光标移到文本的最后
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restoring succeed",Toast.LENGTH_SHORT).show()
        }

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{
            // 地址：/data/data/<package name>/shared_prefs/SavedData
            //Context.MODE_PRIVATE：只有这一种模式了，每次都会覆盖之前的
            val editor = getSharedPreferences("SavedData",Context.MODE_PRIVATE).edit()
            //提交了三种不同类型的数据
            editor.putString("name","Tom")
            editor.putInt("age",28)
            editor.putBoolean("单身dog",true)
            editor.apply()
            Toast.makeText(this,"Data is saved",Toast.LENGTH_SHORT).show()

        }

        val restoreButton : Button = findViewById(R.id.restoreButton)
        restoreButton.setOnClickListener{
            val sharedPreferenceFile = getSharedPreferences("SavedData",Context.MODE_PRIVATE)
            val name = sharedPreferenceFile.getString("name","")
            val age = sharedPreferenceFile.getInt("age",0)
            val single = sharedPreferenceFile.getBoolean("单身dog",true)
            Log.d("MainActivity_sharedpref","name is $name")
            Log.d("MainActivity_sharedpref","age is $age")
            Log.d("MainActivity_sharedpref"," is single dog? $single")

        }
    }
//目的：为了让客户退出程序再进来时保存之前编辑的文字
    override fun onDestroy() {
        super.onDestroy()
        val editText:TextView = findViewById(R.id.edittext)
        val inputText = editText.text.toString()
        save(inputText)
    }
    //页面文字提取并存入对应文件里（不会随着页面销毁而消失）
    private fun save(inputText:String){
        try{
            val output = openFileOutput("FileName_UserInputData", Context.MODE_PRIVATE)
            //mode_append: 在文件后边添加新的data
            //mode_private:覆盖文件之前的文字，换成新的data
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use{
                it.write(inputText)
            }
        }catch(e:IOException){
            e.printStackTrace()
        }
    }
    //文件导入并展示在页面
    fun load():String{
        val content = StringBuilder()
        try{
            // "data"指的是地址： /data/data/com.example.项目名称/files/data文件夹里找FileName_UserInputData这个文件
            val input = openFileInput("FileName_UserInputData")
            val reader = BufferedReader(InputStreamReader(input))
            //将文件数据一行一行读出来
            //读出来放到StringBuilder（变量content里）
            reader.use{
                reader.forEachLine { content.append(it) }
            }

        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}
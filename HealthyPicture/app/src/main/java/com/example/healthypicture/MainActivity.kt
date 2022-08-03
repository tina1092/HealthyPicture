package com.example.healthypicture

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val fromAlbum = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //提取照片并导入到app
        val albumbutton: Button = findViewById(R.id.photoButton)
        albumbutton.setOnClickListener{
            //打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只选择图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }

        //加载数据库
        val dbHelper = MyDatabaseHelpler(this,"UnhealthyGredient.db",1)
        val db = dbHelper.writableDatabase
        val value =  ContentValues()
        value.put("name","代可可脂")
        val ret = db.insert("Ingredient",null,value)
        if(-1 != ret.toInt()){
            Log.d("SQL_ingredient","add success")
        }else{
            Log.d("SQL_ingredient","add failed")
        }

        //开始分析图片数据
        val analyzeBut:Button=findViewById(R.id.analyzeButton)
        analyzeBut.setOnClickListener{
            val checkContain = checkContent()

            if(checkContain){
                //如果含有有害成分，则跳转到ContainUnhealthyFood页面
                Toast.makeText(this,"you click analyze button",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,ContainUnhealtyFood::class.java)
                startActivity(intent)
            }else {
                //如果没有有害成分，跳转到nounhealthyfood页面
                val intent2 = Intent(this, NoUnhealtyFood::class.java)
                startActivity(intent2)
            }
        }

        }
    //查询有害成分
    fun checkContent():Boolean{
        return false
    }




    //图片相关信息
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            fromAlbum ->{
                if(resultCode == Activity.RESULT_OK && data!=null){
                    data.data?.let{uri ->
                        //将选择的图片显示
                        val bitmap = getBitmapFromUri(uri)
                        val imageView: ImageView = findViewById(R.id.imageview)
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }

        }

    }
    //图片相关信息
    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri,"r")?.use{
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}


//数据库相关信息
class MyDatabaseHelpler(val context: Context, name:String, version:Int):
    SQLiteOpenHelper(context,name,null,version){
    //SQL建表定义为一个字符串
    private val createBook = "create table Ingredient("+
            "id integer primary key autoincrement,"+
            "name text)"

    override fun onCreate(db: SQLiteDatabase?) {
        //执行组件sql表格
        if (db != null) {
            db.execSQL(createBook)
        }
        Log.d("SQL_ingredient","create success")
        /*
        val db1 = this.writableDatabase
        Toast.makeText(context,"I can run this part",Toast.LENGTH_SHORT).show()
        val value = ContentValues().apply{
            put("name","代可可脂")
        }
        val ret = db1.insert("Ingredient",null,value)
        if(-1 != ret.toInt()){
            Log.d("SQL_ingredient","add success")
        }else{
            Log.d("SQL_ingredient","add failed")
        }
        */

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
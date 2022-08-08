package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.content.contentValuesOf
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button:Button = findViewById(R.id.createdatabase)
        //这句在启动的时候就会自动调用onCreate fun，从而创立名字为BookStore.db，数据库用的自己写的sqlite
        val dbHelper = MyDatabaseHelpler(this,"BookStore.db",4)
        button.setOnClickListener{
            //writableDatabase：查看dbHelpler里是否有数据，
            // 如果没有的话就运行MyDatabaseHelpler的onCreate()
            dbHelper.writableDatabase
        }
        val addButton:Button = findViewById(R.id.addData)
        addButton.setOnClickListener{
            // dbHelper:整个数据库的对象
            // db：整个数据库写入的对象
            val db = dbHelper.writableDatabase
            val value1 =  ContentValues()
            value1.put("name","three body1")
            value1.put("author","Dan Brown")
            value1.put("pages",354)
            value1.put("price",16.8)


            //第一个是table name（创建的有name,category）
            // 第二个是未添加数据时自动填入的数据（一般都为null）第三个value1里存入的是需要insert的数据
            val ret1 = db.insert("Book",null,value1)

            val value2 = ContentValues().apply {
                put("name","lost song")
                put("author","Dan Brown")
                put("pages",510)
                put("price",16.8)
            }
            //第一个是table name（创建的有name,category）
            // 第二个是未添加数据时自动填入的数据（一般都为null）第三个value1里存入的是需要insert的数据
            val ret2 = db.insert("Book",null,value2)

            for(i in 1..10){
                val value3 = ContentValues().apply{
                    put("name","book name")
                    put("author","book author")
                    put("pages",i*100)
                    put("price",i*10)
                }
                db.insert("Book",null,value3)
            }
            if(-1 != ret1.toInt() && -1 !=ret2.toInt()){
                Toast.makeText(this,"ADD succeed",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"ADD FAILED!",Toast.LENGTH_SHORT).show()

            }
        }

        val updateButton:Button = findViewById(R.id.updata)
        updateButton.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",10.99)
            db.update("Book",values,"name = ?",arrayOf("three body1"))
        }
        val deleteButton:Button = findViewById(R.id.deletedata)
        deleteButton.setOnClickListener{
            val db = dbHelper.writableDatabase
            val ret = db.delete("Book","pages > ?", arrayOf("0"))
            if(-1 == ret.toInt()){
                Toast.makeText(this,"delete failed",Toast.LENGTH_SHORT).show()
            }else{
                 Toast.makeText(this,"delete succeed",Toast.LENGTH_SHORT).show()
            }
        }

        val queryButton:Button = findViewById(R.id.querydata)
        queryButton.setOnClickListener{
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if(cursor.moveToFirst()){
                do{
                   // Toast.makeText(this,"query the data",Toast.LENGTH_SHORT).show()
                    @SuppressLint("Range") val name = cursor.getString(cursor.getColumnIndex("name"))
                    @SuppressLint("Range") val author = cursor.getString(cursor.getColumnIndex("author"))
                    @SuppressLint("Range") val page = cursor.getString(cursor.getColumnIndex("pages"))
                    @SuppressLint("Range") val price = cursor.getString(cursor.getColumnIndex("price"))
                    Log.d("MainActivity_SQL","book name is $name")
                    Log.d("MainActivity_SQL","book author is $author")
                    Log.d("MainActivity_SQL","book page is $page")
                    Log.d("MainActivity_SQL","book price is $price")

                }while(cursor.moveToNext())
            }
            cursor.close()
        }
        val replaceButton:Button = findViewById(R.id.replacedata)
        replaceButton.setOnClickListener{
            val db = dbHelper.writableDatabase
            db.beginTransaction()//开启事务，保证其中一个环节失败之前的都不会运行
            try{
                db.delete("Book",null,null)
                /*
                if(true){
                    //手动抛出异常，让任务失败 ->
                    //目的是证明Transaction真的可以阻止之前运行的指令不生效
                    throw NullPointerException()
                }
                */

                val value = ContentValues().apply{
                    put("name","权力的游戏")
                    put("author","Robin")
                    put("pages",720)
                    put("price",20.85)
                }
                db.insert("Book",null,value)
                db.setTransactionSuccessful()//标明事务运行成功

            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                db.endTransaction()//结束事务
            }
        }
    }
}

class MyDatabaseHelpler(val context: Context,name:String,version:Int):SQLiteOpenHelper(context,name,null,version){
    //SQL建表定义为一个字符串
    private val createBook = "create table Book("+
            "id integer primary key autoincrement,"+
            "author text,"+
            "price real,"+
            "pages integer,"+
            "name text)"
    private val createCategory = "create table Category("+
            "id integer primary key autoincrement,"+
            "category_name text,"+
            "category_id integer)"

    override fun onCreate(db: SQLiteDatabase) {
        //执行组件sql表格

            db.execSQL(createBook)
            db.execSQL(createCategory)

        Toast.makeText(context,"create succeed",Toast.LENGTH_SHORT).show()
    }
    //只写oncreate的话，如果一个表已经创建成功，后续就无法更改了。所以使用onupgrade让先检查是否有之前创建的旧表格
    //如果有旧表格就弃用，然后再执行oncreate
    //onUpgrade会被启用只有在这一行“ val dbHelper = MyDatabaseHelpler(this,"BookStore.db",1)”
    //中，之前已经创建过切版本号改变的情况下会执行onupgrade
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(oldVersion <=1){//比方说，在2之后的所有版本都会在oncreate里增加category表格，
            // 那么如果用户下载的是版本1，为了能兼容，必须要写一个if条件，判断在这个版本之前
                // 的版本要另外添加category
            db.execSQL(createCategory)
        }}
}
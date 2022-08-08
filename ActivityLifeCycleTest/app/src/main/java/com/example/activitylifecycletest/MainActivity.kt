package com.example.activitylifecycletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"onCreate")
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            val tempData = savedInstanceState.getString("这个保存信息的key")
            Log.d(tag,"保存到的信息 is $tempData")
        }
        val startNormalActivity : Button = findViewById(R.id.startNormalActivity)
        startNormalActivity.setOnClickListener{
            val intent = Intent(this,NormalActivity::class.java)
            startActivity(intent)
        }
        val startDialogActivity : Button = findViewById(R.id.startDialogActivity)
        startDialogActivity.setOnClickListener{
            val intent = Intent(this,DialogActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        val tempData = " 你想要保存的信息从而不回因为重新启动activity而被刷新掉"
        outState.putString("这个保存信息的key",tempData)
    }
    override fun onStart(){
        super.onStart()
        Log.d(tag,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestory")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart")
    }
}


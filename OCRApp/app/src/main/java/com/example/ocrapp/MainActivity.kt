package com.example.ocrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val copyBut: Button = findViewById(R.id.copyBut)
    val captureBut:Button = findViewById(R.id.captureBut)
val text_data : TextView = findViewById(R.id.text_data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
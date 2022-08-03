package com.example.healthypicture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NoUnhealtyFood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_unhealty_food)
        val backButton:Button = findViewById(R.id.BackButton2)
        backButton.setOnClickListener{
            backButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
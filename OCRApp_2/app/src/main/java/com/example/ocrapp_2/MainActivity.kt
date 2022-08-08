package com.example.ocrapp_2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val copyBut: Button = findViewById(R.id.copyBut)
    val captureBut: Button = findViewById(R.id.captureBut)
    val text_data : TextView = findViewById(R.id.text_data)
    private val REQUEST_CAMERA_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA_CODE)
        }
        captureBut.setOnClickListener{
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun getTextFromImage( bitmap: Bitmap){
        val recognizer = TextRecognizer.Builder(this).build()
        if(!recognizer.isOperational){
            Toast.makeText(this,"Error in recognize",Toast.LENGTH_SHORT).show()
        }else{
            val frame = Frame.Builder().setBitmap(bitmap).build()
            val textBlockSparseArray = recognizer.detect(frame)//SparseArray<Tex>
            val stringBuilder = StringBuilder()
            for(i in 0 until textBlockSparseArray.size()){
                val textBlock = textBlockSparseArray.valueAt(i)
                stringBuilder.append(textBlock.value)
                stringBuilder.append("\n")
            }
            text_data.setText(stringBuilder.toString())
        }

    }
}
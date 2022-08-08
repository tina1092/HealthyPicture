package com.example.cameraalbumtest

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    //自定义的一个id，和onActivityResult的request code联系在一起，
    // 这样就可以用onActivityResult处理多种人物，比如照相，选图片等等功能
    val fromAlbum = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fromAlbumBtn: Button = findViewById(R.id.formAlbumBtn)
        fromAlbumBtn.setOnClickListener{
            //打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只选择图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }
    }

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

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri,"r")?.use{
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}
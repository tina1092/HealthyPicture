package com.example.activitytest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : BaseActivity() {
    companion object{
        fun actionStart(context: Context, data1:String, data2:String){

            /*
            val intent = Intent(context,SecondActivity::class.java)//在此class里再创建intent
            intent.putExtra("第一个传输进来的数字",data1)
            intent.putExtra("第二个传输进来的数字",data2)
            context.startActivity(intent)//用firstActivity 调用SecondActivity
             */


        //若使用apply函数来写此段代码：
            val intent = Intent(context,SecondActivity::class.java).apply{
                putExtra("第一个传输进来的数字",data1)
                putExtra("第二个传输进来的数字",data2)
            }
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)//和res.layout.activity_second互相关联
        //3.3
        /*
        //拿到从activity1传来的数据
        val extraData = intent.getStringExtra("Key_Tina_UsingThisToFindData")//有getStringExtra,getIntExtra,getBooleanExtra等等
        Log.d("SecondActivity","extra data is $extraData")


        //返回activity1时传递数据回去
        val button2 : Button = findViewById(R.id.button2)//findviewbyid获取layout文件中定义的元素
        button2.setOnClickListener{
            val intent = Intent()
            intent.putExtra("usingThisToFindReturnData","hello this data is from activity 2")
            setResult(RESULT_OK,intent)//setResult用于向上一个activity返回数据：参数一：返回处理结果（是否返回，可以不返回不处理[_CANCELED]）；参数二：把带有的数据的Intent传回去
            finish()//摧毁activity2，返回上一个activity
        }
         */

        //3.5
        /*
        val button2 : Button = findViewById(R.id.button2)//findviewbyid获取layout文件中定义的元素
        button2.setOnClickListener(){
            val intent = Intent(this,FirstActivity::class.java)
            startActivity(intent)
        }

         */

        Log.d("singleTask","Second Task id is $taskId")
        val button2 : Button = findViewById(R.id.button2)//findviewbyid获取layout文件中定义的元素
        button2.setOnClickListener(){
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("usingThisToFindReturnData","hello this data is from activity 2 and activited by press back button")
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("secondActivity","onDestory")
    }
}
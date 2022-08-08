package com.example.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class FirstActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("singleTask"," First Task id is $taskId")
        setContentView(R.layout.first_layout)
        val button1 : Button = findViewById(R.id.button1)//findviewbyid获取layout文件中定义的元素

        button1.setOnClickListener{
            /*
            //Toast.LENGTH_SHORT指的是弹窗持续时间
            Toast.makeText(this,"you clicked button 1",Toast.LENGTH_SHORT).show()
            finish()//结束当前页面
             */
            //3.4
            /*
            //使用显式Intent ， 通过点击button1来进入activity2的页面
            val intent = Intent(this,SecondActivity::class.java)//this指的是firstActivity
            startActivity(intent)
             */

            /*
            //使用隐式Intent，通过点击button1进入activity2的页面
            val intent = Intent("com.example.activitytest.ACTION_START")//这个action字符串和xml里second activity里的intent filter里的name互相对应，从而联系到activity2页面
            intent.addCategory("com.example.activitytest.MY_CATEGORYTINA")//这里添加了一个category，制定了一个自定义category，值为此名字
            startActivity(intent)
            */

            /*
            //使用隐式Intent，通过点击button1进入bilibili网页
            val intent  = Intent(Intent.ACTION_VIEW)//intent.action_view是安卓内置的动作
            intent.data = Uri.parse("https://www.bilibili.com")//使用uri.parse将字符串转换为uri对象，调用intent的setData将这个对象传递进去
            startActivity(intent)
             */

            /*
            //使用intent，拨打电话号码10086
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
             */

            /*
            //传递data字符串放到启用intent的activity里面
            val data = "hello second activity"
            val intent = Intent(this, SecondActivity:: class.java)
            intent.putExtra("Key_Tina_UsingThisToFindData",data)//会传递两个参数，第一个相当于是key，用于当做id；第二个是value是实际要展示的值
            startActivity(intent)
             */
            /*
            //通过标记返回到activity页面的 requestcode设为1从而执行onActivityResult的操作
            val intent = Intent(this,SecondActivity::class.java)
            startActivityForResult(intent,1)
             */

            //3.5 Activity启动模式操作
            /*
            val intent = Intent(this,FirstActivity::class.java)
            startActivity(intent)
             */
            /*
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
             */
            //传递avtivity信息的最佳写法
            SecondActivity.actionStart(this,"想要传输的第一个data","想要传输的第二个data（只需要更改SecondActivity里的actionStart object就可以随意添加任意个数的data）")

        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FirstActivity","onRestart")
    }

//requestCode 就是startActivityForResult(intent,1)里的1
    //onActivityResult与所有调用过startActivityForResult都有联系，所以需要使用when判断requestCode为几，从而做出相应的反应
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if(resultCode == RESULT_OK){
                val returnedData = data?.getStringExtra("usingThisToFindReturnData")
                Log.d("FirstActivity","returned data is $returnedData")
            }
        }
    }
    //实现创建菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
        //用itemid判断点击的是哪一个菜单选项
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this,"you clicked add",Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "you clicked remove",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
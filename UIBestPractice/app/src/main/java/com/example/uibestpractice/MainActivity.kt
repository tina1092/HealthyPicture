package com.example.uibestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private val msgList = ArrayList<Msg>()
    private var adapter:MsgAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMsg()

        val layoutManager = LinearLayoutManager(this)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager=layoutManager

        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        val send : Button = findViewById(R.id.send)
        send.setOnClickListener(this)
    }
    override fun onClick(v:View?){
        val send : Button = findViewById(R.id.send)
        when(v){
            send -> {
                val inputText: TextView = findViewById(R.id.inputText)
                val content = inputText.text.toString()
                if(content.isNotEmpty()){
                    val msg = Msg(content,Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size -1)//当有新消息时，刷新RecyclerView中的显示；msgList.size -1指的是list里边的最后一个Msg
                    val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.scrollToPosition(msgList.size-1)//将recyclerView定位到最后一行
                    inputText.setText(" ")

                }
            }
        }
    }
    private fun initMsg(){
        val msg1 = Msg("Hello guy",Msg.TYPE_RECEIVED)
        val msg2 = Msg("Hello",Msg.TYPE_SENT)
        val msg3 = Msg("Nice to meet you",Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        msgList.add(msg2)
        msgList.add(msg3)

    }


}

class Msg(val content:String,val type:Int){
    //companion:只能存在一个object
    companion object{
        const val TYPE_RECEIVED = 0 //对方发来的短信 -- 代表绿色文本框
        const val TYPE_SENT = 1//我方送过去的短信 -- 代表白色文本框
    }
}
//可以根据不同的viewType创建不同的界面
class MsgAdapter(val msgList:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //用于缓存msg_left_item.xml布局中的控件的文本信息
    inner class LeftViewHolder(view: View):RecyclerView.ViewHolder(view){
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }
    //用于缓存msg_right_item.xml布局中的控件的文本信息
    inner class RightViewHolder(view: View):RecyclerView.ViewHolder(view){
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }
//返回当前postion对应的消息类型
    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    //根据不同的viewType加载不同的布局，并创建不同的viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if(viewType == Msg.TYPE_RECEIVED){//如果收到的是对方发来的短信，则要用left 文本框xml创建layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        LeftViewHolder(view)
    }else{//反之，如果收到的是自己发的短信，则要用right文本框xml创建layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            //创建完layout后，也要保证传输的text value不能出错
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = msgList.size
}
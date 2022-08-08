package com.example.listviewtest

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFruits()//初始fruitList数据
        //由于集合中的数据无法传递给listview，我们需要建立一个adapter（适配器）来传递数据
        //通过魔改ArrayAdapter，我们创建除了一个FruitAdapter专门适配此情况
        //R.layout.fruit_item是res>layout>fruit_item.xml文件，里边放置了一栏里图片和文字如何摆放的
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        val listview : ListView = findViewById(R.id.listview)
        listview.adapter = adapter
        /*
        //由于集合中的数据无法传递给listview，我们需要建立一个adapter（适配器）来传递数据
        //其中适配器里ArrayAdapter 最好用，只需要传入适配的数据
        //android.R.layout.simple_list_item_1:安卓内置的布置文件，不需要管
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)//data已经弃用了，就是一个arraylist
        val listview : ListView = findViewById(R.id.listview)
        listview.adapter = adapter
         */

        listview.adapter = adapter
        //为listview创建了一个监听器
        listview.setOnItemClickListener{
            parent,view,position,id->
            val fruit = fruitList[position]
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }

    }
    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit("a",R.drawable.apple_pic))
            fruitList.add(Fruit("b",R.drawable.banana_pic))
            fruitList.add(Fruit("c",R.drawable.cherry_pic))
            fruitList.add(Fruit("d",R.drawable.grape_pic))
            fruitList.add(Fruit("e",R.drawable.mango_pic))
            fruitList.add(Fruit("f",R.drawable.orange_pic))
            fruitList.add(Fruit("g",R.drawable.pear_pic))

        }
    }
}
class Fruit(val name:String, val imageid: Int)
class FruitAdapter(activity: Activity, val resourceId:Int, data: List<Fruit>):ArrayAdapter<Fruit>(activity,resourceId,data){
    inner class ViewHolder(val fruitImage: ImageView, val fruitName:TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //使用layoutinflater为这个子项加载我们传入的layout，inflate()接受了三个参数，第三个指的是只让父 layout中声明的layout 属性生效，却不会为这个view添加父layout
        //标准写法，直接抄
        val view:View
        val viewHolder: ViewHolder
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
            val fruitName: TextView = view.findViewById(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage,fruitName)
            view.tag = viewHolder // tag可能是一个空的存储室，存啥都行
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder//把存储室里存的东西转换一下格式就又拿出来了
        }

        val fruit = getItem(position)//获取当前项的fruit实例
        if(fruit!=null){
            viewHolder.fruitImage.setImageResource(fruit.imageid)
            viewHolder.fruitName.text = fruit.name
        }
        return view

    }
}
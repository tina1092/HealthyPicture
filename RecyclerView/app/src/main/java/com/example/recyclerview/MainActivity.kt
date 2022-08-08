package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruits()//初始化fruitList
        /*
        //横向布局格式
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
         */
        //创建瀑布流格式（小红书类型）的实例，spanCount指的是一行放几列
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerview)
        //告诉recyclerView，布局方式是什么布局，可以使linearLayout也可以是 StaggeredGridLayout等等
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter

    }
    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit(getRandomLengthString("a"),R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("b"),R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("c"),R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("d"),R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthString("e"),R.drawable.mango_pic))
            fruitList.add(Fruit(getRandomLengthString("f"),R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("g"),R.drawable.pear_pic))

        }
    }
    private fun getRandomLengthString(str:String):String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(str)
        }
        return builder.toString()

    }
}
class Fruit(val name:String, val imageid: Int)
class FruitAdapter(val fruitList:List<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>(){
    //定义了一个内部的viewHolder，这个继承了RecyclerView的viewHolder
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }
//用于创建viewHolder实例的
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)////将fruit_item.xml布局加载进来(R.layout.fruit_item)
        val viewHolder = ViewHolder(view)
    //itemView为最外层布局，只要点击子项中的任意一点都可以被检测到并给出提示
        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context,"you clicked view ${fruit.name}",Toast.LENGTH_SHORT).show()

        }
    //fruitImage就是点击图片了
        viewHolder.fruitImage.setOnClickListener{
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context,"you clicked IMAGE ${fruit.name}",Toast.LENGTH_SHORT).show()
        }
    //fruitName就是点击文字
    viewHolder.fruitName.setOnClickListener{
        val position = viewHolder.adapterPosition
        val fruit = fruitList[position]
        Toast.makeText(parent.context,"you clicked TEXT ${fruit.name}",Toast.LENGTH_SHORT).show()
    }

        return viewHolder
    }
//为RecyclerView的子项赋值，会在当被滚到到屏幕内的子项赋值
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]//获得当前项Fruit实例
    //将实例fruit里的数据放置到对应的图片位置和文字位置上
        holder.fruitImage.setImageResource(fruit.imageid)
        holder.fruitName.text = fruit.name
    }
    //告诉一共有多少个子项
    override fun getItemCount() = fruitList.size

}
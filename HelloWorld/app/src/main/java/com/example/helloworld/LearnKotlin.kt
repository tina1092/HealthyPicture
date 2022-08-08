package com.example.helloworld

import android.util.Log
import kotlin.math.max

fun main(){
    val c1 = Cellphone("apple",12.3)
    val c2 = Cellphone("huawei",12.34)
    println(c1)
    println("c1 equal c2?"+(c1 == c2))
    val a  = 10
    val b = 12
    val maxN = methodName(a,b)
    println("larger num is  : "+maxN)

    val p = Person()
    p.name = "tom"
    p.age = 19
    p.eat()

    //List & Set(set用法和list完全一致)
    //listOf创建只可读不可写的list
    val list1 = listOf("a","b","c")
    for(chara in list1)
        println(chara)
    //mutableListOf创建可更改的list
    val list2  = mutableListOf("a","b","d")
    list2.add("w")
    for(chara in list2)
        println(chara)

    //Map
    val map = HashMap<String, Int>()
    map["a"] = 1
    map["b"] = 2
    val number = map["b"]
        //第二种写法
    val map2 = mapOf("a" to 1,"b" to 2)
    for((fruit, number)in map){
        println(fruit+" "+number)
    }

    //Lambda
    val list_LambdaTest = listOf("apple","banana","orange","watermelon")
        //maxby
   // val maxLengthFruit = list_LambdaTest.maxBy{fruit: String -> fruit.length}
        //map
    val listUpper  = list_LambdaTest.map{it.toUpperCase()}
    for(fruit in listUpper){ println(fruit)}
        //filter
    val ListFilterUpper = list_LambdaTest.filter{it.length <= 5}//先筛出了小于等于5个字母的单词，再全部变成大写
                                        .map{it.toUpperCase()}
        //any & all
    val anyResult = list_LambdaTest.any{it.length <= 5}//boolean:  true
    val allResult = list_LambdaTest.all{it.length <= 5}//boolean:  false






}
fun methodName(param1:Int,para:Int) = max(param1,para)

fun IFMethod(n1:Int,n2:Int)=  if(n1>n2){
        n1
    }else{
        n2
    }

fun WHENmethod(name:String) = when(name){//逻辑与java的while一致，但是更加好用简洁
    "tome" -> 86//匹配值 -> {执行逻辑} ；
    "jim" -> 777
    else -> 0
}
//匹配值可以不是value而是type！（比如还是Int，String...）ex. is Int -> println("number is int")
fun WHENmethod2(num:Number) { //Number的子类有int long float double
    when(num){
        is Int -> println("number is int")
        is Double -> println("number is double")
        else -> println("number not support")
    }
}

fun FORmethod(){
    for(i in 0..10){//[0,10]
        println(i)
    }
    for(i in 0 until 10 step 2){//[0,10);i+=2
        println(i)
    }
    for(i in 10 downTo 1){//[10,1]
        println(i)
    }
}
//当想让传入的参数为空，就加上问号
fun doStudy(study:Study?){
    if(study!=null){
        study.readBooks()
        study.doHomework()
    }
    //等同于
    study?.readBooks()
    study?.doHomework()
    //等同于(let函数运用）
    study?.let{
        it.readBooks()
        it.doHomework()
    }
}
//?.  意思是如果前边的变量为null则不执行，不为null则执行
//a ?: b      意思是如果a不为null就返回a，如果a为null就返回b
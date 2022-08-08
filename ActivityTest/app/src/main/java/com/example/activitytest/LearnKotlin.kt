package com.example.activitytest

fun main(){
    //with用法 比如exam.add(xxx)可以用with简化成 add(xxx)
        //等同于（复杂版本）
    val list = listOf("Appe","banana","Orange")
    val builder = StringBuilder()
    builder.append("Start eating fruits. \n")
    for(fruit in list){
        builder.append(fruit).append("\n")
    }
    builder.append("Ate all fruits")
    val result = builder.toString()
    println(result)
        //with版本
    val list1 = listOf("Appe","banana","Orange")
    val result1 = with(StringBuilder()){
        append("Start eating fruits. \n")
        for(fruit in list1){
            append(fruit).append("\n")
        }
        append("Ate all fruits")
        toString()
    }
    println(result1)

    //run用法 - 与with用法相似
    val list2 = listOf("Appe","banana","Orange")
    val result2 = StringBuilder().run{
        append("Start eating fruits. \n")
        for(fruit in list2){
            append(fruit).append("\n")
        }
        append("Ate all fruits")
        toString()
    }
    println(result2)

    //apply用法-与with，run相似，但是没有return值，变化的是object本身
    val list3 = listOf("Appe","banana","Orange")
    val result3 = StringBuilder().run{
        append("Start eating fruits. \n")
        for(fruit in list3){
            append(fruit).append("\n")
        }
        append("Ate all fruits")
    }
    println(result3.toString())//有变化！


}
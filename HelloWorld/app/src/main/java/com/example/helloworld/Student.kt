package com.example.helloworld

class Student : Person(),Study {
    override fun readBooks() {
        println(name+" is reading")
    }
    override fun doHomework(){
        println(name+" is doinghomework.")
    }

}
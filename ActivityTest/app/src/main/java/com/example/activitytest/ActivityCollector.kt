package com.example.activitytest

import android.app.Activity

object ActivityCollector {
    private val activities = ArrayList<Activity>()
    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    fun removeActivity(acti: Activity){
        activities.remove(acti)
    }

    fun finishAll(){
        for(acti in activities){
            if(!acti.isFinishing){
                acti.finish()
            }
        }
        activities.clear()
    }

}
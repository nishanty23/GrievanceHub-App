package com.example.grievienceapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.grievienceapplication.R

class ActivityMainBinding private constructor(
    val root: View,
    val toolbar: Toolbar,
    val recyclerView: RecyclerView
) {
    companion object {
        fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding {
            val root = layoutInflater.inflate(R.layout.activity_main, null, false)
            val toolbar: Toolbar = root.findViewById(R.id.toolbar)
            val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)

            return ActivityMainBinding(root, toolbar, recyclerView)
        }
    }

    fun setContentView(activity: Activity) {
        activity.setContentView(root)
    }
}
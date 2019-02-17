package com.example.xie.fastlayouttest

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class TestLayoutActivity : Activity() {

    companion object {
        var layoutMap = HashMap<String, MyLayoutPara>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.adapter = SimpleRecyclerAdapter()
    }
}

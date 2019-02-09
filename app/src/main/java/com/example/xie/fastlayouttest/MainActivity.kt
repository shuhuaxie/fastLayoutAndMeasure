package com.example.xie.fastlayouttest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt_test).setOnClickListener()
        { startActivity(Intent(this, TestSimpleActivity::class.java)) }

    }
}

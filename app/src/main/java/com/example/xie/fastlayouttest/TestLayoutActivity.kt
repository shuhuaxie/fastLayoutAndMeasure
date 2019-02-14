package com.example.xie.fastlayouttest

import android.app.Activity
import android.os.Bundle

class TestLayoutActivity : Activity() {

    companion object {
        var layoutMap = HashMap<String, MyLayoutPara>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
    }
}

package com.example.xie.fastlayouttest

import android.app.Activity
import android.os.Bundle
import com.example.xie.longtimeconsumetest.MyLayout.MyLayoutPara

class TestSimpleActivity : Activity() {

    companion object {
        var layoutMap = HashMap<String, MyLayoutPara>()
        var firstOpen = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_simple)
    }
}

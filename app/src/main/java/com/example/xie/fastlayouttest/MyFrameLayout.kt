package com.example.xie.fastlayouttest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout


class MyFrameLayout : FrameLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e("xie", "MyFrameLayout onLayout")
        super.onLayout(changed, l, t, r, b)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e("xie", "MyFrameLayout onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}
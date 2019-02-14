package com.example.xie.fastlayouttest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout


class MyFastLinearLayout : LinearLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (TestSimpleActivity.layoutMap[tag] == null) {
            super.onLayout(changed, l, t, r, b)
            // save the first layout
            var layoutPara = MyLayoutPara()
            layoutPara.width = measuredWidth
            layoutPara.height = measuredHeight
            saveLayoutInfo(this, layoutPara)
            TestSimpleActivity.layoutMap[tag.toString()] = layoutPara
            Log.e("xie", "MyFastLinearLayout onLayout normal")
        } else {
            Log.e("xie", "MyFastLinearLayout onLayout self")
            layoutWithPara(this, TestSimpleActivity.layoutMap[tag])
        }
    }

    private fun layoutWithPara(viewGroup: ViewGroup, myLayoutPara: MyLayoutPara?) {
        for (i in 0 until viewGroup.childCount) {
//            if ((myLayoutPara?.views?.size!! < (i + 1))) {
//                Log.e("xie", "view type:" + this.javaClass.toString())
//                Log.e("xie", "childCount:" + childCount.toString() +
//                        "__size:" + myLayoutPara?.views?.size!!)
//                return
//            }
            var para = myLayoutPara?.views?.get(i)
            para?.let { para ->
                val child = viewGroup.getChildAt(i)?.apply {
                    if (this is ImageView) {
                        // ImageView's setFrame is different
                        val clazz = ImageView::class.java
                        val method = clazz.getDeclaredMethod("setFrame",
                                Int::class.java, Int::class.java, Int::class.java, Int::class.java)
                        method.isAccessible = true
                        method.invoke(this, para!!.left, para.top, para.right, para.bottom)
                    } else {
                        left = para!!.left
                        top = para.top
                        right = para.right
                        bottom = para.bottom
                    }
                }
                if (child is ViewGroup) {
                    layoutWithPara(child, para)
                }
            }

        }

    }

    private fun saveLayoutInfo(viewGroup: ViewGroup, layoutPara: MyLayoutPara) {
        for (i in 0 until viewGroup.childCount) {
            var childPara = MyLayoutPara()
            layoutPara.views.add(childPara)
            val child = viewGroup.getChildAt(i)
            childPara.width = child.measuredWidth
            childPara.height = child.measuredHeight
            childPara.left = child.left
            childPara.top = child.top
            childPara.right = child.right
            childPara.bottom = child.bottom
            if (child is ViewGroup) {
                saveLayoutInfo(child, childPara)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (TestSimpleActivity.layoutMap[tag] == null) {
            Log.e("xie", "MyFastLinearLayout measure normal")
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        } else {
            Log.e("xie", "MyFastLinearLayout measure self")
            var para = TestSimpleActivity.layoutMap[tag]
            setMeasuredDimension(para!!.width,
                    para.height)
        }
    }

}
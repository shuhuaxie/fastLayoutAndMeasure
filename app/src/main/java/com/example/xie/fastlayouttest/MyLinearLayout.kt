package com.example.xie.longtimeconsumetest.MyLayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.xie.fastlayouttest.TestSimpleActivity
import java.lang.reflect.AccessibleObject.setAccessible


class MyLinearLayout : LinearLayout {
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
            Log.e("xie", "LinearLayout onLayout normal")
        } else {
            Log.e("xie", "LinearLayout onLayout self")
            layoutWithPara(this, TestSimpleActivity.layoutMap[tag])
        }
    }

    private fun layoutWithPara(viewGroup: ViewGroup, myLayoutPara: MyLayoutPara?) {
        for (i in 0 until childCount) {
            var para = myLayoutPara?.views?.get(i)
            val child = viewGroup.getChildAt(i).apply {
                // 反射
                try {
                    val clazz1 = View::class.java
                    val method = clazz1.getDeclaredMethod("setMeasuredDimension",
                            Int::class.java, Int::class.java)
                    method.isAccessible = true
                    method.invoke(this, para!!.width,
                            para.height)
                    val method2 = clazz1.getDeclaredMethod("setFrame",
                            Int::class.java, Int::class.java,Int::class.java, Int::class.java)
                    method2.isAccessible = true
                    method2.invoke(this, para.left, para.top, para.right, para.bottom)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (child is ViewGroup) {
                layoutWithPara(child, para)
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
            Log.e("xie", "LinearLayout measure normal")
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        } else {
            Log.e("xie", "LinearLayout measure self")
            var para = TestSimpleActivity.layoutMap[tag]
            setMeasuredDimension(para!!.width,
                    para!!.height)
        }
    }

}
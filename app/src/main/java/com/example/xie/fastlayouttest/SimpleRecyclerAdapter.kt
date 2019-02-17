package com.example.xie.fastlayouttest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class SimpleRecyclerAdapter() : RecyclerView.Adapter<SimpleRecyclerAdapter.VH>() {


    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.title)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.title.text = " rv item :$position"
//        holder.itemView.setOnClickListener(object : View.OnClickListener() {
//            fun onClick(v: View) {
//                //item 点击事件
//            }
//        })
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        var viewHolder = VH(v)
        val clazz = RecyclerView.LayoutParams::class.java
        val field = clazz.getDeclaredField("mViewHolder")
        field.isAccessible = true
        field.set(v.layoutParams, viewHolder)
        return viewHolder
    }
}
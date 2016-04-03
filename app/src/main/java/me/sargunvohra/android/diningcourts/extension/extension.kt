package me.sargunvohra.android.diningcourts.extension

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

fun RecyclerView.setEmptyAdapter() {
    adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onBindViewHolder(vh: RecyclerView.ViewHolder, i: Int) = throw UnsupportedOperationException()
        override fun onCreateViewHolder(vg: ViewGroup, i: Int) = throw UnsupportedOperationException()
        override fun getItemCount() = 0
    }
}
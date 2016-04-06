package me.sargunvohra.android.diningcourts.main.datepicker

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import org.jetbrains.anko.matchParent

class ViewDatePicker(
        context: Context,
        attributes: AttributeSet
) : RecyclerView(context, attributes) {

    init {
        layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        setEmptyAdapter()
    }
}
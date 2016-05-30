package me.sargunvohra.android.diningcourts.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.ViewGroup
import android.view.ViewManager
import khronos.beginningOfDay
import khronos.day
import khronos.plus
import me.sargunvohra.android.diningcourts.component.datepicker.DatePickerView
import me.sargunvohra.android.diningcourts.component.singledate.SingleDateView
import org.jetbrains.anko.custom.ankoView
import java.text.SimpleDateFormat
import java.util.*

fun RecyclerView.setEmptyAdapter() {
    adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onBindViewHolder(vh: RecyclerView.ViewHolder, i: Int) = throw UnsupportedOperationException()
        override fun onCreateViewHolder(vg: ViewGroup, i: Int) = throw UnsupportedOperationException()
        override fun getItemCount() = 0
    }
}

val ClosedRange<Date>.days: List<Date>
    get() = generateSequence(start) {it.beginningOfDay + 1.day}.takeWhile { it in this }.toList()


//fun ViewManager.singleDateView() = singleDateView {}
//inline fun ViewManager.singleDateView(init: SingleDateView.() -> Unit) = ankoView({ SingleDateView(it) }, init)
//
//fun ViewManager.datePickerView() = datePickerView {}
//inline fun ViewManager.datePickerView(init: DatePickerView.() -> Unit) = ankoView({ DatePickerView(it) }, init)

val Context.activity: Activity get() {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity)
            return ctx
        ctx = ctx.baseContext
    }
    throw IllegalStateException()
}
package me.sargunvohra.android.diningcourts.main.datepicker

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import khronos.Dates
import khronos.beginningOfDay
import khronos.day
import me.sargunvohra.android.diningcourts.extension.days
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import me.sargunvohra.android.diningcourts.extension.singleDateView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.UI
import org.jetbrains.anko.info
import java.util.*

class ViewDatePicker(
        context: Context,
        attributes: AttributeSet? = null
) : RecyclerView(context, attributes), AnkoLogger {

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        setEmptyAdapter()
    }

    fun applyDateRange(range: ClosedRange<Date>) {
        val rb = RendererBuilder(object : RosieRenderer<Date>() {

            lateinit var dateView: ViewSingleDate

            override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View = parent.context.UI {
                dateView = singleDateView()
            }.view

            override fun render() {
                dateView.apply {
                    applyDate(content)
                    val mode = when {
                        DateUtils.isToday(content.time) -> ViewSingleDate.Mode.UNSELECTED_TODAY
                        else -> ViewSingleDate.Mode.UNSELECTED
                    }
                    applyMode(mode)
                }
            }
        })
        val adapter = RVRendererAdapter(rb, ListAdapteeCollection(range.days))
        swapAdapter(adapter, false)
    }
}
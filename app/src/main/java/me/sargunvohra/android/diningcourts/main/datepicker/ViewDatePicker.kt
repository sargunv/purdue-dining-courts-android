package me.sargunvohra.android.diningcourts.main.datepicker

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import me.sargunvohra.android.diningcourts.extension.days
import me.sargunvohra.android.diningcourts.extension.setEmptyAdapter
import me.sargunvohra.android.diningcourts.extension.singleDateView
import org.jetbrains.anko.UI
import java.util.*

class ViewDatePicker(
        context: Context,
        attributes: AttributeSet? = null
) : RecyclerView(context, attributes) {

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

            override fun render() = dateView.applyDate(content)
        })
        val adapter = RVRendererAdapter(rb, ListAdapteeCollection(range.days))
        swapAdapter(adapter, false)
    }
}
package me.sargunvohra.android.diningcourts.component.datepicker

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pedrogomez.renderers.ListAdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import me.sargunvohra.android.diningcourts.extension.days
import org.jetbrains.anko.AnkoLogger
import java.util.*

class DatePickerView(
        context: Context,
        attributes: AttributeSet? = null
) : RecyclerView(context, attributes), AnkoLogger {

    private var selected = -1

    private val collection = ListAdapteeCollection(emptyList<DatePickerItem>())
    private val rendererBuilder = RendererBuilder(DatePickerRenderer { onItemClicked(it) })
    private val callbacks = LinkedList<(DatePickerItem) -> Unit>()

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = RVRendererAdapter(rendererBuilder, collection)
    }

    fun applyDateRange(range: ClosedRange<Date>) {
        collection.clear()
        collection.addAll(range.days.mapIndexed { i, date ->
            DatePickerItem(i, date, i == selected)
        })
        adapter.notifyDataSetChanged()
    }

    fun selectItem(index: Int) {
        if (selected >= 0 && selected < collection.size) {
            collection[selected] = collection[selected].copy(false)
            adapter.notifyItemChanged(selected)
        }
        selected = index
        if (selected >= 0 && selected < collection.size) {
            collection[selected] = collection[selected].copy(true)
            adapter.notifyItemChanged(selected)
        }
    }

    fun addItemClickListener(callback: (DatePickerItem) -> Unit) {
        callbacks += callback
    }

    private fun onItemClicked(item: DatePickerItem) {
        callbacks.forEach {
            it(item)
        }
    }
}
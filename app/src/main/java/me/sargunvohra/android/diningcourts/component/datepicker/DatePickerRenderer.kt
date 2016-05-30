package me.sargunvohra.android.diningcourts.component.datepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karumi.rosie.renderer.RosieRenderer
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.component.singledate.SingleDateView
import org.jetbrains.anko.*

class DatePickerRenderer(
        private val onClickCallback: (DatePickerItem) -> Unit
) : RosieRenderer<DatePickerItem>(), AnkoLogger {

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
        return inflater.inflate(R.layout.item_view_date_picker, parent, false)
    }

    override fun render() {
        rootView.find<SingleDateView>(R.id.dateView).apply {
            applyDate(content.date)
            applyMode(content.mode)
            info { "setup click for ${content.index}" }
            onClick {
                info { "clicked ${content.index}!" }
                onClickCallback(content)
            }
        }
    }
}
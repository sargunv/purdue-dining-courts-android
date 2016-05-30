package me.sargunvohra.android.diningcourts.component.datepicker

import android.text.format.DateUtils
import me.sargunvohra.android.diningcourts.component.singledate.SingleDateView
import java.util.*

data class DatePickerItem(
        val index: Int,
        val date: Date,
        val mode: SingleDateView.Mode
) {
    constructor(index: Int, date: Date, selected: Boolean): this(index, date, when {
        selected -> SingleDateView.Mode.SELECTED
        DateUtils.isToday(date.time) -> SingleDateView.Mode.UNSELECTED_TODAY
        else -> SingleDateView.Mode.UNSELECTED
    })

    fun copy(selected: Boolean) = this.copy(mode = when {
        selected -> SingleDateView.Mode.SELECTED
        DateUtils.isToday(date.time) -> SingleDateView.Mode.UNSELECTED_TODAY
        else -> SingleDateView.Mode.UNSELECTED
    })
}
package me.sargunvohra.android.diningcourts.component.singledate

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_single_date.view.*
import me.sargunvohra.android.diningcourts.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor
import java.text.SimpleDateFormat
import java.util.*

class SingleDateView(
        context: Context,
        attributes: AttributeSet? = null
) : LinearLayout(context, attributes) {

    private val dayNameFormat = SimpleDateFormat("EEE")
    private val dayNumFormat = SimpleDateFormat("d")
    private val dateInputFormat = SimpleDateFormat("MM-dd-yyyy")

    init {
        LayoutInflater.from(context).inflate(R.layout.view_single_date, this)
        if (attributes != null) {
            context.theme.obtainStyledAttributes(attributes, R.styleable.SingleDateView, 0, 0).let {
                try {
                    it.getString(R.styleable.SingleDateView_date).let {
                        applyDate(if (it == null) Date() else dateInputFormat.parse(it))
                    }

                    val modeInt = it.getInteger(R.styleable.SingleDateView_mode, Mode.UNSELECTED.ordinal)
                    applyMode(Mode.values()[modeInt])
                } finally {
                    it.recycle()
                }
            }
        } else {
            applyDate(Date())
            applyMode(Mode.UNSELECTED)
        }
    }

    fun applyDate(date: Date) {
        dayOfWeek.text = dayNameFormat.format(date)
        dayOfMonth.text = dayNumFormat.format(date)
    }

    fun applyMode(mode: Mode) {
        when (mode) {
            Mode.UNSELECTED -> {
                dayLayout.backgroundColor = ContextCompat.getColor(context, R.color.accent)
                listOf(dayOfMonth, dayOfWeek).forEach {
                    it.textColor = ContextCompat.getColor(context, R.color.white)
                    it.typeface = Typeface.DEFAULT
                    it.gravity = Gravity.CENTER_HORIZONTAL
                }
            }
            Mode.UNSELECTED_TODAY -> {
                dayLayout.backgroundColor = ContextCompat.getColor(context, R.color.accent)
                listOf(dayOfMonth, dayOfWeek).forEach {
                    it.textColor = ContextCompat.getColor(context, R.color.white)
                    it.typeface = Typeface.DEFAULT_BOLD
                    it.gravity = Gravity.CENTER_HORIZONTAL
                }
            }
            Mode.SELECTED -> {
                dayLayout.backgroundColor = ContextCompat.getColor(context, R.color.accent_light)
                listOf(dayOfMonth, dayOfWeek).forEach {
                    it.textColor = ContextCompat.getColor(context, R.color.primary_text)
                    it.typeface = Typeface.DEFAULT_BOLD
                    it.gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }
    }

    enum class Mode {
        UNSELECTED, UNSELECTED_TODAY, SELECTED
    }
}
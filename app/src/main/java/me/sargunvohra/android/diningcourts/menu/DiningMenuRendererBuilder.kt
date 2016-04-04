package me.sargunvohra.android.diningcourts.menu

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.RendererBuilder
import me.sargunvohra.android.diningcourts.R
import org.jetbrains.anko.*

class DiningMenuRendererBuilder : RendererBuilder<DiningMenuListItem>() {

        init {
        prototypes = listOf(
                LocationHeader(),
                MenuDate(),
                MealHeader(),
                Closed(),
                StationHeader(),
                Item()
        )
    }

    override fun getPrototypeClass(content: DiningMenuListItem) = when(content) {
        is DiningMenuListItem.LocationHeader -> LocationHeader::class
        is DiningMenuListItem.MenuDate -> MenuDate::class
        is DiningMenuListItem.MealHeader -> MealHeader::class
        is DiningMenuListItem.Closed -> Closed::class
        is DiningMenuListItem.StationHeader -> StationHeader::class
        is DiningMenuListItem.Item -> Item::class
    }.java

    private class LocationHeader : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context,
                    size = 24f,
                    font = Typeface.DEFAULT_BOLD
            ).apply { textView = second }.first
        }

        override fun render() {
            textView.text = (content as DiningMenuListItem.LocationHeader).name + " Dining Court"
        }
    }

    private class MenuDate : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context,
                    size = 13f,
                    color = R.color.secondary_text,
                    font = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
            ).apply { textView = second }.first
        }

        override fun render() {
            textView.text = (content as DiningMenuListItem.MenuDate).date
        }
    }

    private class MealHeader : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context,
                    size = 20f,
                    font = Typeface.DEFAULT_BOLD
            ).apply { textView = second }.first
        }

        override fun render() {
            textView.text = (content as DiningMenuListItem.MealHeader).name
        }
    }

    private class Closed : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context,
                    size = 16f,
                    color = R.color.closed,
                    font = Typeface.DEFAULT_BOLD
            ).apply {
                textView = second;
                textView.text = "Closed"
            }.first
        }
    }

    private class StationHeader : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context,
                    size = 16f,
                    font = Typeface.DEFAULT_BOLD
            ).apply { textView = second }.first
        }

        override fun render() {
            textView.text = (content as DiningMenuListItem.StationHeader).name
        }
    }

    private class Item : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return simpleRow(
                    parent.context
            ).apply { textView = second }.first
        }

        override fun render() {
            textView.text = (content as DiningMenuListItem.Item).name
        }
    }
}

private fun simpleRow(
        context: Context,
        size: Float = 13f,
        height: Float = 40f,
        font: Typeface = Typeface.DEFAULT,
        color: Int = R.color.primary_text
): Pair<View, TextView> {
    var textView: TextView? = null
    val rootView = context.UI {
        linearLayout {
            lparams {
                width = matchParent
                this.height = dip(height)
            }
            leftPadding = dip(16)
            rightPadding = dip(16)
            textView = textView {
                textSize = size
                typeface = font
                verticalGravity = Gravity.CENTER_VERTICAL
                textColor = ContextCompat.getColor(context, color)
            }
        }
    }.view
    return rootView to textView!!
}
package me.sargunvohra.android.diningcourts.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.RendererBuilder
import org.jetbrains.anko.UI
import org.jetbrains.anko.textView

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

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView()
        }.view

        override fun render() {
            textView.text = (content as DiningMenuListItem.LocationHeader).name
        }
    }

    private class MenuDate : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView()
        }.view

        override fun render() {
            textView.text = (content as DiningMenuListItem.MenuDate).date
        }
    }

    private class MealHeader : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView()
        }.view

        override fun render() {
            textView.text = (content as DiningMenuListItem.MealHeader).name
        }
    }

    private class Closed : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView {
                text = "Closed"
            }
        }.view
    }

    private class StationHeader : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView()
        }.view

        override fun render() {
            textView.text = (content as DiningMenuListItem.StationHeader).name
        }
    }

    private class Item : RosieRenderer<DiningMenuListItem>() {

        lateinit var textView: TextView;

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
            textView = textView()
        }.view

        override fun render() {
            textView.text = (content as DiningMenuListItem.Item).name
        }
    }
}
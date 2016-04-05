package me.sargunvohra.android.diningcourts.menu

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import com.pedrogomez.renderers.RendererBuilder
import me.sargunvohra.android.diningcourts.R
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

class DiningMenuRendererBuilder : RendererBuilder<DiningMenuListItem>() {

    init {
        prototypes = listOf(
                LocationHeader(),
                MenuDate(),
                MealHeader(),
                Closed(),
                StationHeader(),
                Item(),
                Divider()
        )
    }

    override fun getPrototypeClass(content: DiningMenuListItem) = when (content) {
        is DiningMenuListItem.LocationHeader -> LocationHeader::class
        is DiningMenuListItem.MenuDate -> MenuDate::class
        is DiningMenuListItem.MealHeader -> MealHeader::class
        is DiningMenuListItem.Closed -> Closed::class
        is DiningMenuListItem.StationHeader -> StationHeader::class
        is DiningMenuListItem.Item -> Item::class
        is DiningMenuListItem.Divider -> Divider::class
    }.java

    private class LocationHeader : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_h1, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).text = (content as DiningMenuListItem.LocationHeader).name + " Dining Court"
        }
    }

    private class MenuDate : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_compact, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).text = (content as DiningMenuListItem.MenuDate).date.toString()
        }
    }

    private class MealHeader : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_h2, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).text = (content as DiningMenuListItem.MealHeader).name
        }
    }

    private class Closed : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_h3, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).apply {
                text = "Closed"
                textColor = ContextCompat.getColor(context, R.color.closed)
            }
        }
    }

    private class StationHeader : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_h3, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).text = (content as DiningMenuListItem.StationHeader).name
        }
    }

    private class Item : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_compact, parent, false)
        }

        override fun render() {
            rootView.find<TextView>(R.id.textView).text = (content as DiningMenuListItem.Item).name
        }
    }

    private class Divider : RosieRenderer<DiningMenuListItem>() {

        override fun inflate(inflater: LayoutInflater, parent: ViewGroup): View {
            return inflater.inflate(R.layout.list_item_divider, parent, false)
        }
    }
}
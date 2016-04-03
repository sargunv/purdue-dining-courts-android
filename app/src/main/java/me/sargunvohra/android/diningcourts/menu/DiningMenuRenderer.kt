package me.sargunvohra.android.diningcourts.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import org.jetbrains.anko.UI
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

class DiningMenuRenderer(): RosieRenderer<DiningMenuListItem>() {

    lateinit var text: TextView

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            text = textView()
        }
    }.view

    override fun render() {
        text.text = with(content) {
            when (this) {
                is DiningMenuListItem.LocationHeader -> name
                is DiningMenuListItem.Divider -> "DIVIDER"
                is DiningMenuListItem.MenuDate -> date
                is DiningMenuListItem.MealHeader -> "-$name"
                is DiningMenuListItem.Closed -> "CLOSED"
                is DiningMenuListItem.StationHeader -> "--$name"
                is DiningMenuListItem.Item -> "---$name"
            }
        }
    }
}
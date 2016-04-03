package me.sargunvohra.android.diningcourts.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.karumi.rosie.renderer.RosieRenderer
import me.sargunvohra.android.diningcourts.data.menu.DiningMenu
import org.jetbrains.anko.UI
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

class DiningMenuRenderer(): RosieRenderer<DiningMenu.Item>() {

    lateinit var text: TextView

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup) = parent.context.UI {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            text = textView()
        }
    }.view

    override fun render() {
        text.text = content.name
    }
}
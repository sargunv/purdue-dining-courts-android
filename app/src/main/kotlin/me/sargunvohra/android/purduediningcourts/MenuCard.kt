package me.sargunvohra.android.purduediningcourts

import android.content.Context
import android.text.Html
import android.view.View
import android.view.ViewGroup
import it.gmariotti.cardslib.library.internal.Card

import kotlinx.android.synthetic.card_menu_content.view.*
import org.json.simple.JSONArray
import org.json.simple.JSONObject

public class MenuCard(context: Context, data: JSONArray) : Card(context, R.layout.card_menu_content) {
    private val data = data

    override fun setupInnerViewElements(parent: ViewGroup?, view: View?) {
        super.setupInnerViewElements(parent, view)
        if (parent != null) {
            with(StringBuilder()) {
                if (data.size() == 0) {
                    this.append("""<font color="maroon">Not serving today</font>""")
                } else {
                    data.forEach {
                        if (it is JSONObject) {
                            this.append("<h4>${it["Name"]}</h4><p>")
                            (it["Items"] as JSONArray).forEach {
                                if (it is JSONObject) {
                                    this.append("${it["Name"]}")
                                    if (it["IsVegetarian"] as Boolean)
                                        this.append(""" <font color="green">(V)</font>""")
                                    this.append("<br />")
                                }
                            }
                            this.append("</p>")
                        }
                    }
                }
                parent.card_content.setText(Html.fromHtml(toString()))
            }
        }
    }
}
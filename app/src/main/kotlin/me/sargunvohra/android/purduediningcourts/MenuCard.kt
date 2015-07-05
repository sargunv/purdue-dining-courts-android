package me.sargunvohra.android.purduediningcourts

import android.content.Context
import android.text.Html
import android.view.View
import android.view.ViewGroup
import it.gmariotti.cardslib.library.internal.Card
import kotlinx.android.synthetic.card_menu_content.view.card_content
import me.sargunvohra.android.purduediningcourts.R
import me.sargunvohra.android.purduediningcourts.model.DiningCourtStation

public class MenuCard(context: Context, val menu: List<DiningCourtStation>) : Card(context, R.layout.card_menu_content) {

    override fun setupInnerViewElements(parent: ViewGroup?, view: View?) {
        super.setupInnerViewElements(parent, view)
        if (parent != null) {
            with(StringBuilder()) {
                menu.forEach {
                    this.append("<h4>${it.name}</h4><p>")
                    it.items.forEach {
                        this.append("${it.name}")
                        if (it.isVegetarian)
                            this.append(""" <font color="green">(V)</font>""")
                        this.append("<br />")
                    }
                    this.append("</p>")
                }
                parent.card_content.setText(Html.fromHtml(toString()))
            }
        }
    }
}
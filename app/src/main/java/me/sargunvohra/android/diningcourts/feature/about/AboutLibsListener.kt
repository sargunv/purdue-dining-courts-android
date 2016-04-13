package me.sargunvohra.android.diningcourts.feature.about

import android.content.Intent
import android.net.Uri
import android.view.View
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsConfiguration
import com.mikepenz.aboutlibraries.entity.Library
import me.sargunvohra.android.diningcourts.R

class AboutLibsListener(): LibsConfiguration.LibsListener {

    override fun onExtraClicked(view: View, specialButton: Libs.SpecialButton): Boolean {
        val strRes = when (specialButton) {
            Libs.SpecialButton.SPECIAL1 -> R.string.aboutLibraries_description_special1_text
            Libs.SpecialButton.SPECIAL2 -> R.string.aboutLibraries_description_special2_text
            Libs.SpecialButton.SPECIAL3 -> R.string.aboutLibraries_description_special3_text
        }
        val url = view.context.getString(strRes)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)
        return true
    }

    override fun onIconClicked(v: View?) {
    }

    override fun onLibraryAuthorClicked(v: View?, library: Library?) = false

    override fun onIconLongClicked(v: View?) = false

    override fun onLibraryBottomLongClicked(v: View?, library: Library?) = false

    override fun onLibraryBottomClicked(v: View?, library: Library?) = false

    override fun onLibraryContentClicked(v: View?, library: Library?) = false

    override fun onLibraryAuthorLongClicked(v: View?, library: Library?) = false

    override fun onLibraryContentLongClicked(v: View?, library: Library?) = false

}
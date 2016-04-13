package me.sargunvohra.android.diningcourts.feature.about

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.view.View
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsConfiguration
import com.mikepenz.aboutlibraries.entity.Library
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.extension.activity

class AboutLibsListener() : LibsConfiguration.LibsListener {

    private fun launch(v: View, url: String) = v.context.let { ctx ->
        CustomTabsIntent.Builder().build().launchUrl(ctx.activity, Uri.parse(url))
        true
    }

    override fun onExtraClicked(v: View, specialButton: Libs.SpecialButton): Boolean {
        return launch(v, v.context.getString(
                when (specialButton) {
                    Libs.SpecialButton.SPECIAL1 -> R.string.aboutLibraries_description_special1_text
                    Libs.SpecialButton.SPECIAL2 -> R.string.aboutLibraries_description_special2_text
                    Libs.SpecialButton.SPECIAL3 -> R.string.aboutLibraries_description_special3_text
                }
        ))
    }

    override fun onIconClicked(v: View) {
    }

    override fun onIconLongClicked(v: View) = false

    override fun onLibraryAuthorClicked(v: View, lib: Library) = launch(v, lib.authorWebsite)

    override fun onLibraryAuthorLongClicked(v: View, lib: Library) = launch(v, lib.authorWebsite)

    override fun onLibraryContentClicked(v: View, lib: Library) = launch(v, lib.libraryWebsite)

    override fun onLibraryContentLongClicked(v: View, lib: Library) = launch(v, lib.libraryWebsite)

    override fun onLibraryBottomClicked(v: View, lib: Library) = launch(v, lib.license.licenseWebsite)

    override fun onLibraryBottomLongClicked(v: View, lib: Library) = launch(v, lib.license.licenseWebsite)

}
package me.sargunvohra.android.diningcourts.config

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.GlideModule

class GlideConfig : GlideModule {
    override fun applyOptions(context: Context, glideBuilder: GlideBuilder) {
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }

    override fun registerComponents(context: Context, glide: Glide) {
    }
}
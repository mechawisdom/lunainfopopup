package com.mechawisdom.lunapopupinfo

import android.content.Context
import android.content.res.Configuration
import androidx.core.graphics.toColorInt

class LunaInfoPopupColors(private val context: Context) {
    val backgroundColor: Int
        get() {
            val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                "#d1d2d7".toColorInt()
            } else {
                "#27292E".toColorInt()
            }
        }

    val textColor: Int
        get() {
            val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                "#0d0d0d".toColorInt()
            } else {
                "#F3F3F3".toColorInt()
            }
        }
}

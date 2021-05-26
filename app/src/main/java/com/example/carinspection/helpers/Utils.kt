package com.example.carinspection.helpers

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.carinspection.R
import com.example.carinspection.database.User

fun formatUsers(nights: List<User>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        nights.forEach {
            append("<br>")
            append(it.firstName+" "+it.lastName)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
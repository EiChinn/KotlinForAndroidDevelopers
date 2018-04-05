package com.grgt.learnkotlin.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by JDRJ on 2017/12/13.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}
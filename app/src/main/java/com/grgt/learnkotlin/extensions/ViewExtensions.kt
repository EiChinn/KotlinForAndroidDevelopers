package com.grgt.learnkotlin.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by JDRJ on 2017/12/11.
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

fun View.slideExit() {
    if (translationY == 0.0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0.0f) animate().translationY(0.0f)
}
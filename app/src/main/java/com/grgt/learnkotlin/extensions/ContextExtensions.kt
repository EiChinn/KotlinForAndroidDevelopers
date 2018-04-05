package com.grgt.learnkotlin.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by JDRJ on 2018/3/9.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)
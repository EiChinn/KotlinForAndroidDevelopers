package com.grgt.learnkotlin

import android.util.Log
import java.net.URL

/**
 * Created by JDRJ on 2017/12/8.
 */
public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}
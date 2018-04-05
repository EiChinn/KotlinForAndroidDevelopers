package com.grgt.learnkotlin.data.server

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * Created by JDRJ on 2017/12/8.
 */
class ForecastRequest(val zipCode: Long) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$BASE_URL&APPID=$APP_ID&zip="

    }

    fun execute(): ForecastResult {
        Log.i("tag","request url: " + COMPLETE_URL + zipCode)
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.i("tag", forecastJsonStr)
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}
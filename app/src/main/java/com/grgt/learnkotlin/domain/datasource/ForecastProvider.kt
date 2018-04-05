package com.grgt.learnkotlin.domain.datasource

import com.grgt.learnkotlin.data.db.ForecastDb
import com.grgt.learnkotlin.data.server.ForecastServer
import com.grgt.learnkotlin.domain.model.Forecast
import com.grgt.learnkotlin.domain.model.ForecastList
import com.grgt.learnkotlin.extensions.firstResult

/**
 * Created by JDRJ on 2018/3/8.
 */
class ForecastProvider(val sources: List<ForecastDatasource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf<ForecastDatasource>(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f : (ForecastDatasource) -> T?): T = sources.firstResult{f(it)}
}
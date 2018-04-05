package com.grgt.learnkotlin.domain.datasource

import com.grgt.learnkotlin.domain.model.Forecast
import com.grgt.learnkotlin.domain.model.ForecastList

/**
 * Created by JDRJ on 2018/3/8.
 */
interface ForecastDatasource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}
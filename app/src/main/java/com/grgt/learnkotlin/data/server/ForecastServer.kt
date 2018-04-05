package com.grgt.learnkotlin.data.server

import com.grgt.learnkotlin.data.db.ForecastDb
import com.grgt.learnkotlin.domain.datasource.ForecastDatasource
import com.grgt.learnkotlin.domain.model.Forecast
import com.grgt.learnkotlin.domain.model.ForecastList

/**
 * Created by JDRJ on 2018/3/8.
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDatasource {
    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

}
package com.grgt.learnkotlin.data.db

import com.grgt.learnkotlin.domain.model.Forecast
import com.grgt.learnkotlin.domain.model.ForecastList

/**
 * Created by JDRJ on 2018/3/7.
 */
class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }
    fun convertFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map{convertDayToDomain(it)}
        ForecastList(_id, city, country, daily)
    }
    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }

}
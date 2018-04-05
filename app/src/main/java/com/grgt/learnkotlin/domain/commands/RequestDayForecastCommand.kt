package com.grgt.learnkotlin.domain.commands

import com.grgt.learnkotlin.domain.datasource.ForecastProvider
import com.grgt.learnkotlin.domain.model.Forecast

/**
 * Created by JDRJ on 2018/3/8.
 */
class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast>{
    override fun execute(): Forecast = forecastProvider.requestForecast(id)

}
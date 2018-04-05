package com.grgt.learnkotlin.domain.commands

import com.grgt.learnkotlin.domain.datasource.ForecastProvider
import com.grgt.learnkotlin.domain.model.ForecastList

/**
 * Created by JDRJ on 2017/12/11.
 */
class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    companion object {
        val DAYS = 7
    }
    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)

    }
}
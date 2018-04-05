package com.grgt.learnkotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.grgt.learnkotlin.R
import com.grgt.learnkotlin.domain.commands.RequestForecastCommand
import com.grgt.learnkotlin.extensions.DelegatesExt
import com.grgt.learnkotlin.ui.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val zipCode: Long by DelegatesExt.longPreference(this, SettingActivity.ZIP_CODE, SettingActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync{
        Log.i("tag","start")
        val result = RequestForecastCommand(zipCode).execute()
        Log.i("tag", result.toString())
        uiThread {
            //                forecast_list.adapter = ForecastListAdapter(result,
//                    object : ForecastListAdapter.OnItemClickListener{
//                        override fun invoke(forecast: Forecast) {
//                            toast(forecast.date.toString())
//                        }
//                    })}
            forecast_list.adapter = ForecastListAdapter(result){
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
            }

            toolbar.title = "${result.city} (${result.country})"
        }


    }

}

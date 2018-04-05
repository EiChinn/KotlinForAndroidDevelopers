package com.grgt.learnkotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.grgt.learnkotlin.R
import com.grgt.learnkotlin.domain.model.Forecast
import com.grgt.learnkotlin.domain.model.ForecastList
import com.grgt.learnkotlin.extensions.ctx
import com.grgt.learnkotlin.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by JDRJ on 2017/12/7.
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(val view: View, private val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(view){
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Glide.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
//                itemView.setOnClickListener { itemClick(this) }
                //setOnClickListener in Java
                /*itemView.setOnClickListener(new OnClickListener(View view){
                    public void onClick(View view) {
                        itemClick.invoke(forecast);
                    }
                });*/

                //setOnClickListener in Kotlin
                /*itemView.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(p0: View?) {
                        itemClick.invoke(forecast)
                    }
                })*/

                //simple
                itemView.setOnClickListener({view -> itemClick.invoke(forecast)})

                //simple
                itemView.setOnClickListener({itemClick.invoke(forecast)})

                //simple
                itemView.setOnClickListener() {itemClick.invoke(forecast)}

                //simple
                itemView.setOnClickListener {itemClick.invoke(forecast)}



                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }

    //define interface in Java
    /*public interface OnItemClickListener {
        void invoke(Forecast forecast);
    }*/

    //define interface in Kotlin
    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}
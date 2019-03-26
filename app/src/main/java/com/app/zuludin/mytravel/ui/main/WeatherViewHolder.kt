package com.app.zuludin.mytravel.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Weather
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Weather> {
    override fun bind(item: Weather) {
        itemView.current_weather.text = itemView.context.getString(R.string.weather, item.temperature)
        Picasso.get().load(item.icon).into(itemView.weather_icon)
        itemView.weather_condition.text = item.condition
    }
}
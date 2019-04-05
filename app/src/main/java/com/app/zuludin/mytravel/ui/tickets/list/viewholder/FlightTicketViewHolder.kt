package com.app.zuludin.mytravel.ui.tickets.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.utils.currencyText
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_flight_ticket.view.*

class FlightTicketViewHolder(
    itemView: View,
    private val listener: (flight: Flight, icon: ImageView) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Flight> {
    override fun bind(item: Flight) {
        itemView.origin_time.text = item.departureTime
        itemView.destination_time.text = item.arrivalTime
        itemView.origin_code.text = item.originCode
        itemView.destination_code.text = item.destinationCode
        itemView.origin_city.text = item.originCity
        itemView.destination_city.text = item.destinationCity
//        item.icon?.let { itemView.flight_logo.setImageResource(it) }

        item.price?.let { itemView.flight_price.currencyText(it) }

        itemView.setOnClickListener {
            listener(item, itemView.flight_logo)
        }
    }
}
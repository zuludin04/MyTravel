package com.app.zuludin.mytravel.ui.tickets.review

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.data.model.local.Passenger
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_passenger.view.*

class PassengerViewHolder(
    itemView: View,
    private val listener: PassengerClickListener
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Passenger> {
    override fun bind(item: Passenger) {
        itemView.passenger_text.text = item.name
        itemView.setOnClickListener {
            listener.onClick(adapterPosition, item.type)
        }
    }
}

interface PassengerClickListener {
    fun onClick(position: Int, type: String)
}
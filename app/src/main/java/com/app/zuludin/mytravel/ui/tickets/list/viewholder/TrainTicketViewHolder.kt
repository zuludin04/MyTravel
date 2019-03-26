package com.app.zuludin.mytravel.ui.tickets.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.utils.currencyText
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_train_ticket.view.*

class TrainTicketViewHolder(
    itemView: View,
    private val listener: (train: Train, textTransition: TextView) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Train> {
    override fun bind(item: Train) {
        itemView.argo_train.text = item.argoName
        itemView.train_seat_class.text = item.seatClass

        item.trainPrice?.let { itemView.train_price.currencyText(it) }
        itemView.train_duration.text = item.trainDuration
        itemView.departure_station.text = item.cityOrigin
        itemView.departure_time.text = item.timeOrigin
        itemView.arrival_station.text = item.cityDestination
        itemView.arrival_time.text = item.timeDestination

        itemView.setOnClickListener {
            listener(item, itemView.argo_train)
        }
    }
}
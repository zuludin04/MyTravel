package com.app.zuludin.mytravel.ui.tickets.detail.hotel.room

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.HotelRoom
import com.app.zuludin.mytravel.utils.currencyText
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_hotel_room.view.*

class RoomHotelViewHolder(
    itemView: View,
    private val listener: RoomClickListener
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<HotelRoom> {

    @Suppress("DEPRECATION")
    override fun bind(item: HotelRoom) {
        Picasso.get().load(item.image).into(itemView.hotel_room_image)
        itemView.hotel_room_name.text = item.name
        itemView.hotel_room_price.currencyText(item.price)
        itemView.hotel_room_guest.text = item.guest
        itemView.hotel_room_bed.text = item.bed

        if (item.refundable!!) {
            itemView.hotel_refundable.setTextColor(itemView.context.resources.getColor(R.color.available))
        } else {
            itemView.hotel_refundable.setTextColor(itemView.context.resources.getColor(R.color.unavailable))
        }

        if (item.breakfast!!) {
            itemView.hotel_breakfast.setTextColor(itemView.context.resources.getColor(R.color.available))
        } else {
            itemView.hotel_breakfast.setTextColor(itemView.context.resources.getColor(R.color.unavailable))
        }

        if (item.wifi!!) {
            itemView.hotel_wifi.setTextColor(itemView.context.resources.getColor(R.color.available))
        } else {
            itemView.hotel_wifi.setTextColor(itemView.context.resources.getColor(R.color.unavailable))
        }

        itemView.setOnClickListener { listener.onRoomClick(item) }
    }
}

interface RoomClickListener {
    fun onRoomClick(hotelRoom: HotelRoom)
}
package com.app.zuludin.mytravel.ui.tickets.detail.hotel.room

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.data.model.remote.HotelRoom
import com.app.zuludin.mytravel.utils.currencyText
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_hotel_room.view.*

class RoomHotelViewHolder(
    itemView: View,
    private val listener: RoomClickListener
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<HotelRoom> {

    override fun bind(item: HotelRoom) {
        itemView.hotel_room_name.text = item.name
        itemView.hotel_room_price.currencyText(item.price)
        itemView.hotel_name.text = item.hotel
        itemView.setOnClickListener { listener.onRoomClick(item) }
    }
}

interface RoomClickListener {
    fun onRoomClick(hotelRoom: HotelRoom)
}
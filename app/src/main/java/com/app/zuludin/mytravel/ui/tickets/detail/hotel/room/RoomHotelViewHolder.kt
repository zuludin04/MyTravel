package com.app.zuludin.mytravel.ui.tickets.detail.hotel.room

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.mytravel.data.model.remote.HotelRoom
import com.app.zuludin.mytravel.utils.currencyText
import com.app.zuludin.mytravel.utils.isAvailableColorText
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
        itemView.hotel_refundable.isAvailableColorText(item.refundable!!)
        itemView.hotel_breakfast.isAvailableColorText(item.breakfast!!)
        itemView.hotel_wifi.isAvailableColorText(item.wifi!!)

        itemView.setOnClickListener { listener.onRoomClick(item) }
    }
}

interface RoomClickListener {
    fun onRoomClick(hotelRoom: HotelRoom)
}
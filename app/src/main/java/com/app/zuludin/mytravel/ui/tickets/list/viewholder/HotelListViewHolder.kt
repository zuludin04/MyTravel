package com.app.zuludin.mytravel.ui.tickets.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_hotel_list.view.*
import java.text.NumberFormat
import java.util.*

class HotelListViewHolder(
    itemView: View,
    private val listener: (hotel: Hotel, imageTransition: ImageView) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Hotel> {
    override fun bind(item: Hotel) {
        item.hotelImage?.let { itemView.hotel_image.setImageResource(it) }
        itemView.hotel_name.text = item.hotelName
        itemView.hotel_rating.rating = item.hotelRating?.toFloat()!!

        val localeId = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeId)

        itemView.hotel_price.text =
            itemView.context.getString(R.string.hotel_price, numberFormat.format(item.hotelPrice))

        itemView.hotel_place.text = item.hotelCity

        itemView.setOnClickListener {
            listener(item, itemView.hotel_image)
        }
    }
}
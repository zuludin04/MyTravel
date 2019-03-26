package com.app.zuludin.mytravel.ui.tickets.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.data.model.remote.CarRental
import com.app.zuludin.mytravel.utils.currencyText
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_car_list.view.*

class CarListViewHolder(
    itemView: View,
    private val listener: (rental: CarRental, imageTransition: ImageView) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<CarRental> {
    override fun bind(item: CarRental) {
        item.carImage?.let { itemView.car_image.setImageResource(it) }
        itemView.car_name.text = item.carName
        itemView.rental_name.text = item.rentalName
        itemView.rental_rating.rating = item.rentalRating?.toFloat()!!

        item.rentalPrice?.let { itemView.rental_price.currencyText(it) }

        itemView.setOnClickListener {
            listener(item, itemView.car_image)
        }
    }
}
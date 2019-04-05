package com.app.zuludin.mytravel.ui.tickets.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.data.model.remote.Rental
import com.app.zuludin.mytravel.utils.currencyText
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_car_list.view.*

class CarListViewHolder(
    itemView: View,
    private val listener: (rental: Rental, imageTransition: ImageView) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Rental> {
    override fun bind(item: Rental) {
//        item.image?.let { itemView.car_image.setImageResource(it) }
        itemView.car_name.text = item.car
        itemView.rental_name.text = item.rental
        itemView.rental_rating.rating = item.rating?.toFloat()!!

        item.price?.let { itemView.rental_price.currencyText(it) }

        itemView.setOnClickListener {
            listener(item, itemView.car_image)
        }
    }
}
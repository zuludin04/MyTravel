package com.app.zuludin.mylibrary.dialog.viewholder

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.app.zuludin.mylibrary.dialog.model.Airport
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_helper_list.view.*

class AirportViewHolder(
    itemView: View,
    private val listener: (Airport) -> Unit
): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), SrvViewHolder<Airport> {
    override fun bind(item: Airport) {
        itemView.main_text.text = item.airport
        itemView.additional_text.text = "${item.city}, ${item.province}"

        itemView.setOnClickListener { listener(item) }
    }
}
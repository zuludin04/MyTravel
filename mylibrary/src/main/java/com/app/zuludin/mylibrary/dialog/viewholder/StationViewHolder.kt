package com.app.zuludin.mylibrary.dialog.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mylibrary.dialog.model.Station
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_helper_list.view.*

class StationViewHolder(
    itemView: View,
    private val listener: (Station) -> Unit
): RecyclerView.ViewHolder(itemView), SrvViewHolder<Station> {
    override fun bind(item: Station) {
        itemView.main_text.text = item.station
        itemView.additional_text.text = item.city

        itemView.setOnClickListener { listener(item) }
    }
}
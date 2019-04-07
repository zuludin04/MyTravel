package com.app.zuludin.mylibrary.dialog.viewholder

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.app.zuludin.mylibrary.dialog.model.City
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_helper_list.view.*

class CityViewHolder(
    itemView: View,
    private val listener: (City) -> Unit
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), SrvViewHolder<City> {
    override fun bind(item: City) {
        itemView.main_text.text = item.cityName
        itemView.additional_text.text = item.province

        itemView.setOnClickListener { listener(item) }
    }
}
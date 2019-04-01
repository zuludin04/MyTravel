package com.app.zuludin.mytravel.ui.category

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_explore_place.view.*

class CategoryViewHolder(
    itemView: View,
    private val listener: (image: ImageView, item: TravelExplore) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<TravelExplore> {
    override fun bind(item: TravelExplore) {
        Picasso.get().load(item.thumbnail).into(itemView.explore_image)
        itemView.explore_category.text = item.category
        itemView.explore_name.text = item.name
        itemView.setOnClickListener { listener(itemView.explore_image, item) }
    }
}
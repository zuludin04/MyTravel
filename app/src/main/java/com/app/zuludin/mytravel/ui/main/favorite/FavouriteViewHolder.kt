package com.app.zuludin.mytravel.ui.main.favorite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.mytravel.data.model.local.Favourite
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_explore_place.view.*

class FavouriteViewHolder(
    itemView: View,
    private val listener: (image: ImageView, item: Favourite) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Favourite> {
    override fun bind(item: Favourite) {
        Picasso.get().load(item.thumbnail).into(itemView.explore_image)
        itemView.explore_category.text = item.category
        itemView.explore_name.text = item.name
        itemView.setOnClickListener { listener(itemView.explore_image, item) }
    }
}
package com.app.zuludin.mytravel.ui.main.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.ExploreItem
import com.app.zuludin.mytravel.data.model.local.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelData
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import com.tomasznajda.simplerecyclerview.adapter.BasicSrvAdapter
import kotlinx.android.synthetic.main.item_explore_place.view.*
import kotlinx.android.synthetic.main.item_horizontal_recycler.view.*

class ExploreListViewHolder(
    itemView: View,
    private val listener: (image: ImageView, item: TravelExplore) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<ExploreList> {
    private val adapter: BasicSrvAdapter by lazy {
        BasicSrvAdapter().apply {
            addViewHolder(TravelExplore::class, R.layout.item_explore_place) { ExploreItemViewHolder(it, listener) }
        }
    }

    override fun bind(item: ExploreList) {
        itemView.list_title.text = item.title
        itemView.recycler_explore.apply {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ExploreListViewHolder.adapter
        }
        adapter.items = item.explores
    }
}

class ExploreItemViewHolder(
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
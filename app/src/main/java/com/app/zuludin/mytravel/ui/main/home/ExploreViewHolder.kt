package com.app.zuludin.mytravel.ui.main.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.ExploreItem
import com.app.zuludin.mytravel.data.model.local.ExploreList
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import com.tomasznajda.simplerecyclerview.adapter.BasicSrvAdapter
import kotlinx.android.synthetic.main.item_explore_place.view.*
import kotlinx.android.synthetic.main.item_horizontal_recycler.view.*

class ExploreListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SrvViewHolder<ExploreList> {
    private val adapter: BasicSrvAdapter by lazy {
        BasicSrvAdapter().apply {
            addViewHolder(ExploreItem::class, R.layout.item_explore_place) { ExploreItemViewHolder(it) }
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

class ExploreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SrvViewHolder<ExploreItem> {
    override fun bind(item: ExploreItem) {
        itemView.explore_image.setImageResource(item.image)
        itemView.explore_category.text = item.category
        itemView.explore_name.text = item.name
    }
}
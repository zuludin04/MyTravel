package com.app.zuludin.mytravel.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.MainExplore
import com.app.zuludin.mytravel.data.model.remote.Explore
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import com.tomasznajda.simplerecyclerview.adapter.BasicSrvAdapter
import kotlinx.android.synthetic.main.item_explore_list.view.*
import kotlinx.android.synthetic.main.item_recycler_list.view.*

class MainExploreViewHolder(
    itemView: View,
    private val listener: (String) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<MainExplore> {
    private val adapter: BasicSrvAdapter by lazy {
        BasicSrvAdapter().apply {
            addViewHolder(Explore::class, R.layout.item_explore_list) {
                ExploreViewHolder(
                    it, listener
                )
            }
        }
    }

    override fun bind(item: MainExplore) {
        itemView.item_recycler.layoutManager = LinearLayoutManager(itemView.context)
        itemView.item_recycler.addItemDecoration(SpacingItemDecoration(16))
        adapter.items = item.explores
        itemView.item_recycler.adapter = adapter
    }
}

class ExploreViewHolder(
    itemView: View,
    private val listener: (String) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Explore> {
    override fun bind(item: Explore) {
        itemView.explore_image.setImageResource(item.image)
        itemView.explore_name.text = item.name
        itemView.explore_activities.text = item.activities

        itemView.setOnClickListener {
            listener(item.name)
        }
    }
}
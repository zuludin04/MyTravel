package com.app.zuludin.mytravel.ui.main

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Menu
import com.app.zuludin.mytravel.data.model.local.TravelMenuList
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import com.tomasznajda.simplerecyclerview.adapter.BasicSrvAdapter
import kotlinx.android.synthetic.main.item_recycler_list.view.*
import kotlinx.android.synthetic.main.item_travel_menu.view.*

class TravelMenuViewHolder(
    itemView: View,
    private val listener: (String) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<TravelMenuList> {
    private val menuAdapter: BasicSrvAdapter by lazy {
        BasicSrvAdapter().apply {
            addViewHolder(Menu::class, R.layout.item_travel_menu) {
                MenuViewHolder(
                    it, listener
                )
            }
        }
    }

    override fun bind(item: TravelMenuList) {
        menuAdapter.items = item.menuList
        itemView.item_recycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = menuAdapter
        }
    }
}

class MenuViewHolder(
    itemView: View,
    private val listener: (String) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Menu> {
    override fun bind(item: Menu) {
        itemView.setOnClickListener {
            when (item.title) {
                "Food" -> Toast.makeText(itemView.context, "Food", Toast.LENGTH_SHORT).show()
                "Top Up" -> Toast.makeText(itemView.context, "Top Up", Toast.LENGTH_SHORT).show()
                else -> {
                    listener(item.title)

                }
            }
        }

        itemView.travel_icon.setImageResource(item.icon)
        itemView.travel_title.text = item.title
    }
}
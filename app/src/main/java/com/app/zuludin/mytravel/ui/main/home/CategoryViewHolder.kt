package com.app.zuludin.mytravel.ui.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.CategoryItem
import com.app.zuludin.mytravel.data.model.local.CategoryList
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import com.tomasznajda.simplerecyclerview.adapter.BasicSrvAdapter
import kotlinx.android.synthetic.main.item_explore_category.view.*
import kotlinx.android.synthetic.main.item_horizontal_recycler.view.*

class CategoryListViewHolder(
    itemView: View,
    private val listener: (item: CategoryItem, position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<CategoryList> {
    private val adapter: BasicSrvAdapter by lazy {
        BasicSrvAdapter().apply {
            addViewHolder(CategoryItem::class, R.layout.item_explore_category) { CategoryItemViewHolder(it, listener) }
        }
    }

    override fun bind(item: CategoryList) {
        itemView.list_title.text = item.title
        itemView.recycler_explore.apply {
            layoutManager = LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = this@CategoryListViewHolder.adapter
        }
        adapter.items = item.items
    }
}

class CategoryItemViewHolder(
    itemView: View,
    private val listener: (item: CategoryItem, position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView), SrvViewHolder<CategoryItem> {
    override fun bind(item: CategoryItem) {
        itemView.category_image.setImageResource(item.image)
        itemView.category_name.text = item.item
        itemView.setOnClickListener { listener(item, adapterPosition) }
    }
}
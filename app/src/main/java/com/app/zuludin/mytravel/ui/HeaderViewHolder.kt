package com.app.zuludin.mytravel.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.data.model.local.Header
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_header_text.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Header> {
    override fun bind(item: Header) {
        itemView.header_text.text = item.header
    }
}
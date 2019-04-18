package com.app.zuludin.mylibrary.floathint.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.mylibrary.R
import kotlinx.android.synthetic.main.item_spinner.view.*

class FloatingSpinnerAdapter(
    private val context: Context,
    private val items: MutableList<String>,
    private val listener: (item: String, position: Int) -> Unit
) : RecyclerView.Adapter<FloatingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloatingViewHolder =
        FloatingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FloatingViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }
}

class FloatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: String, listener: (item: String, position: Int) -> Unit) {
        if (adapterPosition == 0) {
            itemView.container.setBackgroundColor(itemView.context.resources.getColor(android.R.color.darker_gray))
        }

        itemView.spinner_item.text = item
        itemView.setOnClickListener { listener(item, adapterPosition) }
    }
}
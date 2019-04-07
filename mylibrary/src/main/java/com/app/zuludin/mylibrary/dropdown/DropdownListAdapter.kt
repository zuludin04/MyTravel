package com.app.zuludin.mylibrary.dropdown

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.zuludin.mylibrary.R

class DropdownListAdapter(
    private val context: Context,
    private val items: MutableList<String>,
    private val listener: (String) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<DropdownViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropdownViewHolder =
        DropdownViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.dropdown_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DropdownViewHolder, position: Int) {
        holder.dropdownItem.text = items[position]
        holder.setListener(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class DropdownViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val dropdownItem: TextView = view.findViewById(R.id.item_text)

    fun setListener(item: String, listener: (String) -> Unit) {
        itemView.setOnClickListener { listener(item) }
    }
}

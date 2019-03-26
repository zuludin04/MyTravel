package com.app.zuludin.mytravel.ui.tickets.detail.rental

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R

class CarReviewAdapter : RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(p0: CarViewHolder, p1: Int) {

    }
}

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
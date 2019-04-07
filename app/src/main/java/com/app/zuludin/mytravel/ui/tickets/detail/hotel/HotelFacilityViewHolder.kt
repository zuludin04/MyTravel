package com.app.zuludin.mytravel.ui.tickets.detail.hotel

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.data.model.remote.Facility
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_hotel_facility.view.*

class HotelFacilityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), SrvViewHolder<Facility> {
    override fun bind(item: Facility) {
        Picasso.get().load(item.icon).into(itemView.facility_image)
    }
}
package com.app.zuludin.mytravel.ui.main.transactions

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.tomasznajda.simplerecyclerview.SrvViewHolder
import kotlinx.android.synthetic.main.item_transaction_travel.view.*

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SrvViewHolder<Transaction> {
    override fun bind(item: Transaction) {
        itemView.transaction_main_information.text = item.book
        itemView.transaction_city.text = item.city
        itemView.transaction_type.text = item.type
        itemView.transaction_price.text = item.price

        if (item.status == "Process") {
            itemView.transaction_status.setTextColor(Color.RED)
        } else {
            itemView.transaction_status.setTextColor(itemView.context.resources.getColor(R.color.primaryTextColor))
        }

        itemView.transaction_status.text = item.status
    }
}
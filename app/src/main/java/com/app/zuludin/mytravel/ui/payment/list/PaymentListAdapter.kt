package com.app.zuludin.mytravel.ui.payment.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Payment
import kotlinx.android.synthetic.main.item_payment_type.view.*

class PaymentListAdapter(
    private val items: List<Payment>,
    private val listener: (Payment) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<PaymentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder =
        PaymentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_payment_type,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class PaymentViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    fun bind(payment: Payment, listener: (Payment) -> Unit) {
        itemView.payment_icon.setImageResource(payment.icon)
        itemView.payment_name.text = payment.name
        itemView.payment_description.text = payment.description
        itemView.setOnClickListener { listener(payment) }
    }
}
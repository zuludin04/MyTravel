package com.app.zuludin.mytravel.ui.main.transactions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction

class TransactionAdapter(private val listener: (Transaction) -> Unit) : RecyclerView.Adapter<TransactionViewHolder>() {

    private val dataList: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder =
        TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_transaction_travel,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener { listener(dataList[position]) }
    }

    override fun getItemCount(): Int = dataList.size

    fun refreshAdapterList(list: List<Transaction>) {
        val callback = MyDiffUtilCallback(dataList, list)
        val result = DiffUtil.calculateDiff(callback)

        dataList.clear()
        dataList.addAll(list)
        result.dispatchUpdatesTo(this)
    }
}

class MyDiffUtilCallback(
    private val oldData: List<Transaction>,
    private val newData: List<Transaction>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean =
        oldData[oldPosition].book == newData[newPosition].book

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean = true
}
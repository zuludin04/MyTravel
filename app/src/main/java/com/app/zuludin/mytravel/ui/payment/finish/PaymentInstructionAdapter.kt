package com.app.zuludin.mytravel.ui.payment.finish

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import kotlinx.android.synthetic.main.item_payment_instruction.view.*

class PaymentInstructionAdapter : RecyclerView.Adapter<InstructionViewHolder>() {

    val items: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder =
        InstructionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_payment_instruction,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun refreshList(list: List<String>) {
        val diffCallback = InstructionDiffUtilCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}

class InstructionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(instruction: String) {
        itemView.instruction_number.text = "${adapterPosition.plus(1)}."
        itemView.instruction_text.text = instruction
    }
}

class InstructionDiffUtilCallback(
    private val oldItems: List<String>,
    private val newItems: List<String>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean =
        oldItems[oldPosition] == newItems[newPosition]

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean = true
}
package com.app.zuludin.mytravel.ui.payment.finish.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.finish.PaymentInstructionAdapter
import com.app.zuludin.mytravel.ui.payment.finish.select.PaymentSelectFragment
import com.app.zuludin.mytravel.ui.payment.finish.success.PaymentSuccessFragment
import kotlinx.android.synthetic.main.payment_confirm_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PaymentConfirmFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.payment_confirm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction: Transaction = arguments?.getParcelable(PaymentSelectFragment.TRANSACTION)!!
        val instructions: ArrayList<String> = arguments?.getStringArrayList("INSTRUCTIONS")!!

        view.transaction_main_information.text = transaction.book
        view.transaction_date.text = transaction.date
        view.transaction_price.text = transaction.price
        view.payment_method.text = transaction.method

        val adapter = PaymentInstructionAdapter()

        view.recycler_instructions.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    requireContext(),
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                )
            )
            this.adapter = adapter
        }

        adapter.refreshList(instructions)

        view.confirm_button.setOnClickListener {
            fragmentManager?.beginTransaction()?.run {
                add(android.R.id.content, PaymentSuccessFragment.getInstance(transaction))
                commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) confirmCancel()
        return super.onOptionsItemSelected(item)
    }

    private fun confirmCancel() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Cancel this transaction?")
        builder.setPositiveButton("Ok") { _, _ -> activity?.onBackPressed() }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    companion object {
        fun getInstance(transaction: Transaction, instructions: ArrayList<String>): PaymentConfirmFragment {
            val fragment = PaymentConfirmFragment()
            val args = Bundle()

            args.putParcelable(PaymentSelectFragment.TRANSACTION, transaction)
            args.putStringArrayList("INSTRUCTIONS", instructions)
            fragment.arguments = args

            return fragment
        }
    }
}

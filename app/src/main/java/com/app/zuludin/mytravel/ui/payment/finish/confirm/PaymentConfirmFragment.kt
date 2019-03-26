package com.app.zuludin.mytravel.ui.payment.finish.confirm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
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
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
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

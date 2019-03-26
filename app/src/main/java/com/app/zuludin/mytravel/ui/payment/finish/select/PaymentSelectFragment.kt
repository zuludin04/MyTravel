package com.app.zuludin.mytravel.ui.payment.finish.select

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.finish.PaymentInstructionAdapter
import com.app.zuludin.mytravel.ui.payment.finish.confirm.PaymentConfirmFragment
import com.app.zuludin.mytravel.utils.DataProvider.getAtmTransferInstructions
import com.app.zuludin.mytravel.utils.DataProvider.getMerchantPaymentTransactions
import com.app.zuludin.mytravel.utils.DataProvider.getMobileBankingTransactions
import kotlinx.android.synthetic.main.payment_select_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 *
 */
class PaymentSelectFragment : Fragment() {

    private lateinit var instructionAdapter: PaymentInstructionAdapter
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.payment_select_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type = arguments?.getString(PAYMENT_TYPE).toString()
        instructionAdapter = PaymentInstructionAdapter()

        view.recycler_instructions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            this.adapter = instructionAdapter
        }

        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerItems(type)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        if (type == "ATM" || type == "Bank Transfer" || type == "M-Banking") {
            setupSpinnerInstructionList(view, adapter)
        } else {
            view.pick_payment_method.visibility = View.GONE
            view.merchant.visibility = View.VISIBLE
            view.payment_method_unselected.visibility = View.GONE
            view.payment_merchant.text = type
            instructionAdapter.refreshList(getMerchantPaymentTransactions())
        }

        view.proceed_button.setOnClickListener {
            view.progress_layout.visibility = View.VISIBLE
            val transaction: Transaction = arguments?.getParcelable(TRANSACTION)!!
            transaction.method = paymentName(view)

            GlobalScope.launch(Dispatchers.Main) {
                delay(2000)

                fragmentManager?.beginTransaction()?.run {
                    setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    replace(
                        R.id.frame_container,
                        PaymentConfirmFragment.getInstance(transaction, instructionAdapter.items)
                    )
                    commit()
                }
            }
        }
    }

    private fun paymentName(view: View): String {
        return if (type == "ATM" || type == "Bank Transfer" || type == "M-Banking") {
            "$type ${view.pick_payment_method.selectedItem}"
        } else {
            type
        }
    }

    private fun setupSpinnerInstructionList(view: View, spinnerAdapter: ArrayAdapter<String>) {
        view.pick_payment_method.adapter = spinnerAdapter

        view.pick_payment_method.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                if (position >= 0) {
                    view.payment_method_unselected.visibility = View.GONE
                    view.recycler_instructions.visibility = View.VISIBLE
                    val bank = spinnerItems(type)[position]
                    instructionAdapter.refreshList(instructionList(bank))
                } else {
                    view.payment_method_unselected.visibility = View.VISIBLE
                    view.recycler_instructions.visibility = View.GONE
                }
            }
        }
    }

    private fun instructionList(bank: String): List<String> {
        val instructions: MutableList<String> = mutableListOf()

        when (type) {
            "ATM" -> instructions.addAll(getAtmTransferInstructions(false, bank))
            "M-Banking" -> instructions.addAll(getMobileBankingTransactions(bank))
            "Bank Transfer" -> instructions.addAll(getAtmTransferInstructions(true, "Transfer"))
        }

        return instructions
    }

    private fun spinnerItems(type: String): Array<String> {
        var list: Array<String> = arrayOf()

        when (type) {
            "ATM" -> {
                list = resources.getStringArray(R.array.atm_transfer_banks)
            }
            "Bank Transfer" -> {
                list = resources.getStringArray(R.array.atm_transfer_banks)
            }
            "M-Banking" -> {
                list = resources.getStringArray(R.array.mbanking_type)
            }
            else -> Log.d("type", "This type is not fit")
        }

        return list
    }

    private fun isPaymentSelected(view: View): Boolean {
        if (type == "ATM" || type == "Bank Transfer" || type == "M-Banking") {
            return view.pick_payment_method.selectedItem != "Payment"
        }
        return true
    }

    companion object {
        const val PAYMENT_TYPE = "paymentType"
        const val TRANSACTION = "transaction"

        fun getInstance(type: String, transaction: Transaction): PaymentSelectFragment {
            val fragment = PaymentSelectFragment()
            val args = Bundle()

            args.putString(PAYMENT_TYPE, type)
            args.putParcelable(TRANSACTION, transaction)
            fragment.arguments = args

            return fragment
        }
    }
}

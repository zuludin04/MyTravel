package com.app.zuludin.mytravel.ui.payment.finish.select

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.finish.PaymentInstructionAdapter
import com.app.zuludin.mytravel.ui.payment.finish.confirm.PaymentConfirmFragment
import com.app.zuludin.mytravel.utils.DataProvider.getAtmTransferInstructions
import com.app.zuludin.mytravel.utils.DataProvider.getMerchantPaymentTransactions
import com.app.zuludin.mytravel.utils.DataProvider.getMobileBankingTransactions
import com.app.zuludin.mytravel.utils.begone
import com.app.zuludin.mytravel.utils.visible
import kotlinx.android.synthetic.main.payment_select_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

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
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
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
            view.pick_payment_method.begone()
            view.merchant.visible()
            view.payment_method_unselected.begone()
            view.payment_merchant.text = type
            instructionAdapter.refreshList(getMerchantPaymentTransactions())
        }

        view.proceed_button.setOnClickListener {
            view.progress_layout.visible()
            val transaction: Transaction = arguments?.getParcelable(TRANSACTION)!!
            transaction.method = paymentName(view)
            transaction.code = codeGenerator()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun codeGenerator(): String {
        val list: ArrayList<Int> = ArrayList()

        for (i in 0 until 8) {
            list.add(randomInt())
        }

        val builder = StringBuilder()

        for (i in list.indices) {
            builder.append(list[i])
        }

        return builder.toString()
    }

    private fun randomInt(): Int {
        val random = Random()
        return random.nextInt(9 - 1 + 1) + 1
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
                    view.payment_method_unselected.begone()
                    view.recycler_instructions.visible()
                    val bank = spinnerItems(type)[position]
                    instructionAdapter.refreshList(instructionList(bank))
                } else {
                    view.payment_method_unselected.visible()
                    view.recycler_instructions.begone()
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

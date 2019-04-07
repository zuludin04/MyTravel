package com.app.zuludin.mytravel.ui.payment.finish.success

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.main.MainActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.payment_success_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 *
 */
class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.payment_success_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction: Transaction = arguments?.getParcelable(ITEM_TRANSACTION)!!

        view.payment_method.text = transaction.method
        view.payment_price.text = transaction.price

        val database = FirebaseDatabase.getInstance().reference
        val transRef = database.child("Transaction")

        transRef.push().setValue(transaction)
            .addOnSuccessListener {
                GlobalScope.launch(Dispatchers.Main) {
                    view.progress_bar.visibility = View.GONE
                    view.check_image.visibility = View.VISIBLE
                    view.payment_process.text = "Transaction Success"

                    delay(2500)

                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            }
    }

    companion object {
        const val ITEM_TRANSACTION = "itemTransaction"

        fun getInstance(transaction: Transaction): PaymentSuccessFragment {
            val fragment = PaymentSuccessFragment()
            val args = Bundle()

            args.putParcelable(ITEM_TRANSACTION, transaction)
            fragment.arguments = args

            return fragment
        }
    }
}

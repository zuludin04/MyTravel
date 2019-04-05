package com.app.zuludin.mytravel.ui.tickets.detail.train

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.databinding.DetailTrainFragmentBinding
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_train_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailTrainFragment : Fragment() {

    private lateinit var transaction: Transaction
    private lateinit var binding: DetailTrainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_train_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction = Transaction()
        setupDetailLayout(view)

        view.payment_button.setOnClickListener {
            val intent = Intent(requireContext(), ReviewTicketActivity::class.java)
            intent.putExtra("Transaction", transaction)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        }
    }

    private fun setupDetailLayout(view: View) {
        val train: Train = arguments?.getParcelable(TRAIN_DATA)!!

        binding.train = train

        val childPrice = train.price?.div(2)
        val totalAdultPrice = train.price?.times(train.adult!!)
        val totalChildPrice = childPrice?.times(train.child!!)
        val total = totalAdultPrice?.plus(totalChildPrice!!)

        view.total_price.currencyText(total)

        transaction.city = getString(R.string.city_trip, train.cityOrigin, train.cityDestination)
        transaction.book = getString(R.string.city_trip, train.stationOrigin, train.stationDestination)
        transaction.price = view.total_price.text.toString()
        transaction.service = "Argo : ${train.argo}"
        transaction.date = "Date : ${train.date}"
        transaction.duration = "Duration : ${train.duration}"
        transaction.type = "Train"
        transaction.adult = train.adult
        transaction.child = train.child
        transaction.infant = 0
    }

    companion object {
        const val TRAIN_DATA = "trainData"

        fun getInstance(train: Train): DetailTrainFragment {
            val fragment = DetailTrainFragment()
            val args = Bundle()

            args.putParcelable(TRAIN_DATA, train)
            fragment.arguments = args

            return fragment
        }
    }
}

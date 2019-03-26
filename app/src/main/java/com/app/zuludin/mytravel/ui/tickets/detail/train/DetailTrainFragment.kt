package com.app.zuludin.mytravel.ui.tickets.detail.train

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_train_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailTrainFragment : Fragment() {

    private lateinit var transaction: Transaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_train_fragment, container, false)
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

        view.detail_argo_name.text = train.argoName
        view.detail_trip_city.text = getString(R.string.city_trip, train.cityOrigin, train.cityDestination)
        view.train_trip_duration.text = train.trainDuration

        view.train_trip_origin.setAnchor(train.departureDate)
        view.train_trip_origin.setTitle(train.stationOrigin)
        view.train_trip_origin.setSubtitle("${train.cityOrigin} - ${train.codeOrigin} (${train.timeOrigin})")

        view.train_trip_destination.setAnchor(train.departureDate)
        view.train_trip_destination.setTitle(train.stationDestination)
        view.train_trip_destination.setSubtitle("${train.cityDestination} - ${train.codeDestination} (${train.timeDestination})")

        val childPrice = train.trainPrice?.div(2)

        val totalAdultPrice = train.trainPrice?.times(train.adult!!)
        val totalChildPrice = childPrice?.times(train.child!!)

        view.adult_price_title.text = "${train.argoName} (Adult) ${train.adult}"
        view.child_price_title.text = "${train.argoName} (Child) ${train.child}"

        view.adult_price.currencyText(totalAdultPrice)
        view.child_price.currencyText(totalChildPrice)

        val total = totalAdultPrice?.plus(totalChildPrice!!)

        view.total_price.currencyText(total)

        transaction.city = getString(R.string.city_trip, train.cityOrigin, train.cityDestination)
        transaction.book = getString(R.string.city_trip, train.stationOrigin, train.stationDestination)
        transaction.price = view.total_price.text.toString()
        transaction.service = "Argo : ${train.argoName}"
        transaction.date = "Date : ${train.departureDate}"
        transaction.duration = "Duration : ${train.trainDuration}"
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

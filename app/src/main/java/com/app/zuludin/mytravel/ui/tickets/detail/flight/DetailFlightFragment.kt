package com.app.zuludin.mytravel.ui.tickets.detail.flight

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.databinding.DetailFlightFragmentBinding
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_flight_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFlightFragment : Fragment() {

    private lateinit var transaction: Transaction
    private lateinit var binding: DetailFlightFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_flight_fragment, container, false)
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
        val flight: Flight = arguments?.getParcelable(FLIGHT_DATA)!!

        binding.flight = flight

        val childPrice = flight.price?.div(2)
        val totalAdultPrice = flight.price?.times(flight.adult!!)
        val totalChildPrice = childPrice?.times(flight.child!!)
        val totalInfantPrice = childPrice?.times(flight.infant!!)
        val total = totalAdultPrice?.plus(totalChildPrice!!)?.plus(totalInfantPrice!!)

        view.total_price.currencyText(total)

        transaction.city = getString(R.string.city_trip, flight.originCity, flight.destinationCity)
        transaction.book = getString(R.string.city_trip, flight.airportOrigin, flight.airportDestination)
        transaction.price = view.total_price.text.toString()
        transaction.service = "Airline : ${flight.airline}"
        transaction.date = "Date : ${flight.date}"
        transaction.duration = "Duration : ${flight.duration}"
        transaction.type = "Flight"
        transaction.adult = flight.adult
        transaction.child = flight.child
        transaction.infant = flight.infant
    }

    companion object {
        const val FLIGHT_DATA = "flightData"

        fun getInstance(flight: Flight): DetailFlightFragment {
            val fragment = DetailFlightFragment()
            val args = Bundle()

            args.putParcelable(FLIGHT_DATA, flight)
            fragment.arguments = args

            return fragment
        }
    }
}

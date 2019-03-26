package com.app.zuludin.mytravel.ui.tickets.detail.flight

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_flight_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFlightFragment : Fragment() {

    private lateinit var transaction: Transaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_flight_fragment, container, false)
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

        view.detail_airline_icon.setImageResource(flight.airlineIcon!!)
        view.detail_airline_name.text = flight.airlineName
        view.detail_trip_city.text = getString(R.string.city_trip, flight.originCity, flight.destinationCity)
        view.detail_trip_duration.text = flight.flightDuration
        view.detail_trip_machine.text = flight.machine
        view.detail_flight_cabin.text = flight.baggage
        view.detail_trip_origin.setTitle(flight.airportOrigin)
        view.detail_trip_origin.setSubtitle("${flight.originCity} - ${flight.originCode} (${flight.originTime})")
        view.detail_trip_origin.setAnchor(flight.originDate)
        view.detail_trip_destination.setTitle(flight.airportDestination)
        view.detail_trip_destination.setSubtitle("${flight.destinationCity} - ${flight.destinationCode} (${flight.destinationTime})")
        view.detail_trip_destination.setAnchor(flight.destinationDate)
        view.adult_price_title.text = "${flight.airlineName} (Adult) ${flight.adultPassenger}"
        view.child_price_title.text = "${flight.airlineName} (Child) ${flight.childPassenger}"
        view.infant_price_title.text = "${flight.airlineName} (Infant) ${flight.infantPassenger}"

        val childPrice = flight.flightPrice?.div(2)

        val totalAdultPrice = flight.flightPrice?.times(flight.adultPassenger!!)
        val totalChildPrice = childPrice?.times(flight.childPassenger!!)
        val totalInfantPrice = childPrice?.times(flight.infantPassenger!!)

        view.adult_price.currencyText(totalAdultPrice)
        view.child_price.currencyText(totalChildPrice)
        view.infant_price.currencyText(totalInfantPrice)

        val total = totalAdultPrice?.plus(totalChildPrice!!)?.plus(totalInfantPrice!!)

        view.total_price.currencyText(total)

        transaction.city = getString(R.string.city_trip, flight.originCity, flight.destinationCity)
        transaction.book = getString(R.string.city_trip, flight.airportOrigin, flight.airportDestination)
        transaction.price = view.total_price.text.toString()
        transaction.service = "Airline : ${flight.airlineName}"
        transaction.date = "Date : ${flight.originDate}"
        transaction.duration = "Duration : ${flight.flightDuration}"
        transaction.type = "Flight"
        transaction.adult = flight.adultPassenger
        transaction.child = flight.childPassenger
        transaction.infant = flight.infantPassenger
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

package com.app.zuludin.mytravel.ui.tickets.search.flight

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.zuludin.mylibrary.dialog.SearchHelperDialogFragment
import com.app.zuludin.mylibrary.dialog.listener.AirportListener
import com.app.zuludin.mylibrary.dialog.listener.CalendarListener
import com.app.zuludin.mylibrary.dialog.model.Airport
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.ui.tickets.list.TicketListActivity
import com.app.zuludin.mytravel.utils.DataProvider.dropdownList
import kotlinx.android.synthetic.main.search_flight_tickets_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchFlightTicketsFragment : Fragment() {

    private lateinit var itemView: View
    private lateinit var flight: Flight

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_flight_tickets_fragment, container, false)
        itemView = view
        flight = Flight()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchLayout()

        view.search_ticket.setOnClickListener {
            flight.adult = itemView.adult.getItemText().toInt()
            flight.child = itemView.child.getItemText().toInt()
            flight.infant = itemView.infant.getItemText().toInt()
            flight.seatClass = itemView.seat_class.getItemText()
            flight.date = itemView.departure_date.getItemText()

            if (isSearchable()) {
                val intent = Intent(requireContext(), TicketListActivity::class.java)
                intent.putExtra(TicketListActivity.LIST_TYPE, "Flight")
                intent.putExtra(TicketListActivity.TICKET_DATA, flight)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            } else {
                Toast.makeText(requireContext(), "Not searchable", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSearchLayout() {
        val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        itemView.departure_date.changeDropdownItem(formatDate.format(Date()))

        itemView.adult.addDropdownItem(dropdownList(requireContext(), R.array.adult))
        itemView.child.addDropdownItem(dropdownList(requireContext(), R.array.child))
        itemView.infant.addDropdownItem(dropdownList(requireContext(), R.array.child))
        itemView.seat_class.addDropdownItem(dropdownList(requireContext(), R.array.airport_class))

        itemView.origin_airport.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Airport", "List", object : AirportListener {
                override fun onAirportClick(airport: Airport) {
                    itemView.origin_airport.changeDropdownItem(airport.airport + " (${airport.code})")
                    flight.airportOrigin = airport.airport
                    flight.originCity = airport.city
                    flight.originCode = airport.code
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.destination_airport.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Airport", "List", object : AirportListener {
                override fun onAirportClick(airport: Airport) {
                    itemView.destination_airport.changeDropdownItem("${airport.airport} (${airport.code})")
                    flight.airportDestination = airport.airport
                    flight.destinationCity = airport.city
                    flight.destinationCode = airport.code
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.departure_date.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Calendar", "Calendar", calendarListener =  object : CalendarListener {
                override fun onSelectDate(selectedDate: Long) {
                    val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    itemView.departure_date.changeDropdownItem(date.format(selectedDate))
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }
    }

    private fun isSearchable(): Boolean {
        if (itemView.origin_airport.getItemText() == "Initial airport" || itemView.destination_airport.getItemText() == "Arrival airport" ||
            itemView.seat_class.getItemText() == "Pick seat class"
        ) {
            return false
        }
        return true
    }
}

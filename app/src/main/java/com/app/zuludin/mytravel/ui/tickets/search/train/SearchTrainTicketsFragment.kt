package com.app.zuludin.mytravel.ui.tickets.search.train

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.zuludin.mylibrary.dialog.SearchHelperDialogFragment
import com.app.zuludin.mylibrary.dialog.listener.CalendarListener
import com.app.zuludin.mylibrary.dialog.listener.StationListener
import com.app.zuludin.mylibrary.dialog.model.Station
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.ui.tickets.list.TicketListActivity
import com.app.zuludin.mytravel.utils.DataProvider.dropdownList
import kotlinx.android.synthetic.main.search_train_tickets_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchTrainTicketsFragment : Fragment() {

    private lateinit var itemView: View
    private lateinit var train: Train

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_train_tickets_fragment, container, false)

        itemView = view
        train = Train()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchLayout()

        view.search_ticket.setOnClickListener {
            if (isSearchable()) {
                train.adult = itemView.adult.getItemText().toInt()
                train.child = itemView.child.getItemText().toInt()
                train.seatClass = itemView.seat_class.getItemText()
                train.date = itemView.departure_date.getItemText()

                val intent = Intent(requireContext(), TicketListActivity::class.java)
                intent.putExtra(TicketListActivity.LIST_TYPE, "Train")
                intent.putExtra(TicketListActivity.TICKET_DATA, train)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            } else {
                Toast.makeText(requireContext(), "Input Properly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSearchLayout() {
        val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        itemView.departure_date.changeDropdownItem(formatDate.format(Date()))

        itemView.adult.addDropdownItem(dropdownList(requireContext(), R.array.adult))
        itemView.child.addDropdownItem(dropdownList(requireContext(), R.array.child))
        itemView.seat_class.addDropdownItem(dropdownList(requireContext(), R.array.airport_class))

        itemView.departure_date.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Calendar", "Calendar", calendarListener = object :
                CalendarListener {
                override fun onSelectDate(selectedDate: Long) {
                    val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    itemView.departure_date.changeDropdownItem(date.format(selectedDate))
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.origin_station.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Station", "List", stationListener = object : StationListener {
                override fun onStationClick(station: Station) {
                    itemView.origin_station.changeDropdownItem(station.station + " (${station.code})")
                    train.stationOrigin = station.station
                    train.cityOrigin = station.city
                    train.codeOrigin = station.code
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.destination_station.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Station", "List", stationListener = object : StationListener {
                override fun onStationClick(station: Station) {
                    itemView.destination_station.changeDropdownItem("${station.station} (${station.code})")
                    train.stationDestination = station.station
                    train.cityDestination = station.city
                    train.codeDestination = station.code
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }
    }

    private fun isSearchable(): Boolean {
        if (itemView.origin_station.getItemText() == "Initial station" ||
            itemView.destination_station.getItemText() == "Arrival station" ||
            itemView.seat_class.getItemText() == "Pick seat"
        ) {
            return false
        }
        return true
    }
}

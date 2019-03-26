package com.app.zuludin.mytravel.ui.tickets.search.rental

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.zuludin.mylibrary.dialog.SearchHelperDialogFragment
import com.app.zuludin.mylibrary.dialog.listener.CalendarListener
import com.app.zuludin.mylibrary.dialog.listener.CityListener
import com.app.zuludin.mylibrary.dialog.model.City
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.CarRental
import com.app.zuludin.mytravel.ui.tickets.list.TicketListActivity
import com.app.zuludin.mytravel.utils.DataProvider.dropdownList
import kotlinx.android.synthetic.main.search_rental_car_list_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchRentalCarListFragment : Fragment() {

    private lateinit var itemView: View
    private lateinit var rental: CarRental

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_rental_car_list_fragment, container, false)

        itemView = view
        rental = CarRental()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchLayout()

        view.search_rental.setOnClickListener {
            if (isSearchable()) {
                rental.rentalDuration = itemView.rental_duration.getItemText()
                rental.pickupTime = itemView.car_pickup_time.getItemText()
                rental.startDate = itemView.rental_date.getItemText()
                rental.rentalLocation = itemView.region_city.getItemText()

                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val duration = itemView.rental_duration.getItemText()
                val calendar = Calendar.getInstance()

                calendar.time = dateFormat.parse(itemView.rental_date.getItemText())
                duration.toInt().let { calendar.add(Calendar.DATE, it) }
                rental.finishDate = dateFormat.format(calendar.time)

                val intent = Intent(requireContext(), TicketListActivity::class.java)
                intent.putExtra(TicketListActivity.LIST_TYPE, "Rental")
                intent.putExtra(TicketListActivity.TICKET_DATA, rental)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            } else {
                Toast.makeText(requireContext(), "Input properly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSearchLayout() {
        val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        itemView.rental_date.changeDropdownItem(formatDate.format(Date()))

        itemView.rental_duration.addDropdownItem(dropdownList(requireContext(), R.array.rental_duration))
        itemView.car_seat.addDropdownItem(dropdownList(requireContext(), R.array.car_seat))

        itemView.rental_date.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Calendar", "Calendar", calendarListener = object :
                CalendarListener {
                override fun onSelectDate(selectedDate: Long) {
                    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    itemView.rental_date.changeDropdownItem(dateFormat.format(selectedDate))
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.region_city.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("City", "List", cityListener = object : CityListener {
                override fun onCityClick(city: City) {
                    itemView.region_city.changeDropdownItem(city.cityName)
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }
    }

    private fun isSearchable(): Boolean {
        if (itemView.region_city.getItemText() == "Select region") {
            return false
        }
        return true
    }
}

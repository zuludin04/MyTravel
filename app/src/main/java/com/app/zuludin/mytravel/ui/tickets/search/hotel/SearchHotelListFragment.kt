package com.app.zuludin.mytravel.ui.tickets.search.hotel

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.zuludin.mylibrary.dialog.SearchHelperDialogFragment
import com.app.zuludin.mylibrary.dialog.listener.CalendarListener
import com.app.zuludin.mylibrary.dialog.listener.CityListener
import com.app.zuludin.mylibrary.dialog.model.City
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.ui.tickets.list.TicketListActivity
import com.app.zuludin.mytravel.utils.DataProvider.dropdownList
import com.apps.zuludin.mylibrary.dropdown.SelectedItemListener
import kotlinx.android.synthetic.main.search_hotel_list_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchHotelListFragment : Fragment() {

    private lateinit var itemView: View
    private lateinit var hotel: Hotel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_hotel_list_fragment, container, false)
        itemView = view
        hotel = Hotel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchLayout()

        view.search_hotel.setOnClickListener {
            hotel.city = itemView.hotel_city.getItemText()
            hotel.checkIn = itemView.check_in_hotel.getItemText()
            hotel.checkOut = itemView.check_out_hotel.getItemText()
            hotel.duration = itemView.total_night.getItemText().toInt()
            hotel.guest = itemView.hotel_guest.getItemText()
            hotel.room = itemView.hotel_room.getItemText()

            if (isSearchable()) {
                val intent = Intent(requireContext(), TicketListActivity::class.java)
                intent.putExtra(TicketListActivity.LIST_TYPE, "Hotel")
                intent.putExtra(TicketListActivity.TICKET_DATA, hotel)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            } else {
                Toast.makeText(requireContext(), "Input properly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSearchLayout() {
        val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val currentDate = Date()
        itemView.check_in_hotel.changeDropdownItem(formatDate.format(currentDate))

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        itemView.check_out_hotel.changeDropdownItem(formatDate.format(calendar.time))

        itemView.total_night.setSelectedListener(object : SelectedItemListener {
            override fun onItemSelected(item: String) {
                val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

                val cal = Calendar.getInstance()

                cal.time = date.parse(itemView.check_in_hotel.getItemText())

                cal.add(Calendar.DATE, item.toInt())
                itemView.check_out_hotel.changeDropdownItem(date.format(cal.time))
            }
        })
        itemView.total_night.addDropdownItem(dropdownList(requireContext(), R.array.night_stay))

        itemView.hotel_room.addDropdownItem(dropdownList(requireContext(), R.array.room_hotel))
        itemView.hotel_guest.addDropdownItem(dropdownList(requireContext(), R.array.guest_hotel))

        itemView.check_in_hotel.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("Calendar", "Calendar", calendarListener = object :
                CalendarListener {
                override fun onSelectDate(selectedDate: Long) {
                    val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    itemView.check_in_hotel.changeDropdownItem(date.format(selectedDate))

                    val cal = Calendar.getInstance()
                    cal.time = date.parse(date.format(selectedDate))
                    cal.add(Calendar.DATE, itemView.total_night.getItemText().toInt())
                    itemView.check_out_hotel.changeDropdownItem(date.format(cal.time))
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }

        itemView.hotel_city.setOnClickListener {
            val dialog = SearchHelperDialogFragment.getInstance("City", "List", cityListener = object : CityListener {
                override fun onCityClick(city: City) {
                    itemView.hotel_city.changeDropdownItem(city.cityName)
                }
            })
            dialog.show(requireFragmentManager(), "Dialog")
        }
    }

    private fun isSearchable(): Boolean {
        if (itemView.hotel_city.getItemText() == "Select region") {
            return false
        }
        return true
    }
}

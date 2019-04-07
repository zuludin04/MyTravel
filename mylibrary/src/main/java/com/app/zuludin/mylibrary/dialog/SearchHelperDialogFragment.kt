package com.app.zuludin.mylibrary.dialog

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.app.zuludin.mylibrary.R
import com.app.zuludin.mylibrary.dialog.listener.AirportListener
import com.app.zuludin.mylibrary.dialog.listener.CalendarListener
import com.app.zuludin.mylibrary.dialog.listener.CityListener
import com.app.zuludin.mylibrary.dialog.listener.StationListener
import com.app.zuludin.mylibrary.dialog.model.Airport
import com.app.zuludin.mylibrary.dialog.model.City
import com.app.zuludin.mylibrary.dialog.model.Station
import com.app.zuludin.mylibrary.dialog.viewholder.AirportViewHolder
import com.app.zuludin.mylibrary.dialog.viewholder.CityViewHolder
import com.app.zuludin.mylibrary.dialog.viewholder.StationViewHolder
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.helper_dialog_fragment.view.*
import java.util.*

class SearchHelperDialogFragment : BaseDialogFragment() {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(Airport::class, R.layout.item_helper_list) {
                AirportViewHolder(it) { airport ->
                    airportListener.onAirportClick(airport)
                    dismiss()
                }
            }
            addViewHolder(City::class, R.layout.item_helper_list) {
                CityViewHolder(it) { city ->
                    cityListener.onCityClick(city)
                    dismiss()
                }
            }
            addViewHolder(Station::class, R.layout.item_helper_list) {
                StationViewHolder(it) { station ->
                    stationListener.onStationClick(station)
                    dismiss()
                }
            }
        }
    }

    private lateinit var airportListener: AirportListener
    private lateinit var cityListener: CityListener
    private lateinit var stationListener: StationListener
    private lateinit var calendarListener: CalendarListener

    fun setAirportListener(listener: AirportListener?) {
        airportListener = listener!!
    }

    fun setCityListener(listener: CityListener?) {
        cityListener = listener!!
    }

    fun setStationListener(listener: StationListener?) {
        stationListener = listener!!
    }

    fun setCalendarListener(listener: CalendarListener?) {
        calendarListener = listener!!
    }

    override fun layoutId(): Int = R.layout.helper_dialog_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.set(ArrayList())

        val title = arguments?.getString(DIALOG_TITLE).toString()
        view.dialog_title.text = title
        setupLayoutHelper(view, title)

        view.close_page.setOnClickListener { dismiss() }
    }

    private fun setupLayoutHelper(view: View, title: String) {
        val type = arguments?.getString(TYPE_VIEW)

        if (type == "List") {
            view.recycler_helper.apply {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
                addItemDecoration(
                    androidx.recyclerview.widget.DividerItemDecoration(
                        requireContext(),
                        androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                    )
                )
                adapter = this@SearchHelperDialogFragment.adapter
            }
            view.recycler_helper.visibility = View.VISIBLE
            when (title) {
                "Airport" -> adapter.insert(airports())
                "City" -> adapter.insert(cities())
                "Station" -> adapter.insert(stations())
            }
        } else {
            val calendarView = view.calendar_view
            view.calendar.visibility = View.VISIBLE

            val calendar = Calendar.getInstance()

            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

            view.confirm_button.setOnClickListener {
                calendarListener.onSelectDate(calendar.timeInMillis)
                dismiss()
            }

            view.cancel_button.setOnClickListener { dismiss() }
        }
    }

    private fun airports(): List<Airport> {
        val items: MutableList<Airport> = mutableListOf()

        items.add(Airport("Husein Sastranegara", "BDO", "Bandung", "West Java"))
        items.add(Airport("Halim Perdanakusuma", "HLP", "Jakarta", "Jakarta"))
        items.add(Airport("Sokarno-Hatta", "CGK", "Tangerang", "Banten"))
        items.add(Airport("Adisutjipto", "JOG", "Sleman", "Yogyakarta"))
        items.add(Airport("Adusumarmo", "SOC", "Boyolali", "Central Java"))
        items.add(Airport("Achmad Yani", "SRG", "Semarang", "Central Java"))
        items.add(Airport("Juanda", "SUB", "Sidoarjo", "East Java"))
        items.add(Airport("Ngurah Rai", "DPS", "Denpasar", "Bali"))
        items.add(Airport("Lombok Praya", "LOP", "Lombok", "West Nusa Tenggara"))
        items.add(Airport("El Tari", "KOE", "Kupang", "East Nusa Tenggara"))
        items.add(Airport("Sentani", "DJJ", "Jayapura", "Papua"))
        items.add(Airport("Pattimura", "AMQ", "Ambon", "Maluku"))
        items.add(Airport("Mopah", "MKQ", "Merauke", "Papua"))
        items.add(Airport("Sam Ratulangi", "MDC", "Manado", "North Sulawesi"))
        items.add(Airport("Supadio", "PNK", "Pontianak", "West Kalimantan"))
        items.add(Airport("Sultan Aji Muhammad Sulaiman", "BPN", "Balikpapan", "East Kalimantan"))
        items.add(Airport("Hang Nadim", "BTH", "Batam", "Riau"))
        items.add(Airport("Sultan Iskandar Muda", "BTJ", "Banda Aceh", "Aceh"))
        items.add(Airport("Polonia", "MES", "Medan", "North Sumatra"))
        items.add(Airport("Sultam Mahmud Badaruddin II", "PLM", "Palembang", "South Sumatra"))

        return items
    }

    private fun cities(): List<City> {
        val items: MutableList<City> = mutableListOf()

        items.add(City("Bogor", "West Java"))
        items.add(City("Bandung", "West Java"))
        items.add(City("Malang", "East Java"))
        items.add(City("Surabaya", "East Java"))
        items.add(City("Yogyakarta", "DIY Yogyakarta"))
        items.add(City("Semarang", "Central Java"))
        items.add(City("South Jakarta", "DKI Jakarta"))
        items.add(City("West Jakarta", "DKI Jakarta"))
        items.add(City("Central Jakarta", "DKI Jakarta"))
        items.add(City("Bali", "Bali"))
        items.add(City("Bekasi", "West Java"))
        items.add(City("Blitar", "East Java"))
        items.add(City("Boyolali", "Central Java"))
        items.add(City("Brebes", "Central Java"))
        items.add(City("Garut", "West Java"))
        items.add(City("Jember", "East Java"))
        items.add(City("Jepara", "Central Java"))
        items.add(City("Kediri", "East Java"))
        items.add(City("Kudus", "Central Java"))
        items.add(City("Banjarnegara", "Central Java"))

        return items
    }

    private fun stations(): List<Station> {
        val items: MutableList<Station> = mutableListOf()

        items.add(Station("Merak Station", "Merak", "MER", "Banten"))
        items.add(Station("Serang Station", "Serang", "SG", "Banten"))
        items.add(Station("Tangerang Station", "Tangerang", "TNG", "Banten"))
        items.add(Station("Yogyakarta Station", "Yogyakarta", "YK", "Yogyakarta"))
        items.add(Station("Gambir Station", "Central Jakarta", "GMR", "DKI Jakarta"))
        items.add(Station("Manggarai Station", "South Jakarta", "MRI", "DKI Jakarta"))
        items.add(Station("Pasar Senen Station", "Central Jakarta", "PSE", "DKI Jakarta"))
        items.add(Station("Bandung Station", "Bandung", "BD", "West Java"))
        items.add(Station("Bekasi Station", "Bekasi", "BKS", "West Java"))
        items.add(Station("Cimahi Station", "Cimahi", "CMI", "West Java"))
        items.add(Station("Ambarawa Station", "Semarang", "ABR", "Central Java"))
        items.add(Station("Solo Balapan Station", "Surakarta", "SLO", "Central Java"))
        items.add(Station("Kebumen Station", "Kebumen", "KM", "Central Java"))
        items.add(Station("Klaten Station", "Klaten", "KT", "Central Java"))
        items.add(Station("Purwokerto Station", "Banyumas", "PWT", "Central Java"))
        items.add(Station("Blitar Station", "Blitar", "BL", "East Java"))
        items.add(Station("Kediri Station", "Kediri", "KD", "East Java"))
        items.add(Station("Malang Station", "Malang", "ML", "East Java"))
        items.add(Station("Surabaya City Station", "Surabaya", "SB", "East Java"))
        items.add(Station("Sidoarjo Station", "Sidoarjo", "SDA", "East Java"))

        return items
    }

    companion object {
        private const val DIALOG_TITLE = "dialogTitle"
        private const val TYPE_VIEW = "typeView"

        fun getInstance(
            title: String,
            type: String,
            airportListener: AirportListener? = null,
            cityListener: CityListener? = null,
            stationListener: StationListener? = null,
            calendarListener: CalendarListener? = null
        ): SearchHelperDialogFragment {
            val fragment = SearchHelperDialogFragment()
            val args = Bundle()

            args.putString(DIALOG_TITLE, title)
            args.putString(TYPE_VIEW, type)

            when (title) {
                "Airport" -> fragment.setAirportListener(airportListener)
                "Station" -> fragment.setStationListener(stationListener)
                "City" -> fragment.setCityListener(cityListener)
                "Calendar" -> fragment.setCalendarListener(calendarListener)
            }

            fragment.arguments = args

            return fragment
        }
    }
}
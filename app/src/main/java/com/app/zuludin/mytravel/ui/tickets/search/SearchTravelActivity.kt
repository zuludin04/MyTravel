package com.app.zuludin.mytravel.ui.tickets.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.ui.tickets.search.flight.SearchFlightTicketsFragment
import com.app.zuludin.mytravel.ui.tickets.search.hotel.SearchHotelListFragment
import com.app.zuludin.mytravel.ui.tickets.search.rental.SearchRentalCarListFragment
import com.app.zuludin.mytravel.ui.tickets.search.train.SearchTrainTicketsFragment
import com.app.zuludin.mytravel.utils.addFragment
import kotlinx.android.synthetic.main.search_travel_activity.*

class SearchTravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_travel_activity)

        val pageTitle = intent.getStringExtra(TRAVEL_SEARCH)

        setSupportActionBar(toolbar)
        supportActionBar?.title = pageTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupFragment(pageTitle)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    private fun setupFragment(fragmentConst: String) {
        when (fragmentConst) {
            "Flight" -> addFragment(SearchFlightTicketsFragment(), R.id.frame_container)
            "Hotel" -> addFragment(SearchHotelListFragment(), R.id.frame_container)
            "Train" -> addFragment(SearchTrainTicketsFragment(), R.id.frame_container)
            "Rental" -> addFragment(SearchRentalCarListFragment(), R.id.frame_container)
        }
    }

    companion object {
        const val TRAVEL_SEARCH = "travel_search"
    }
}

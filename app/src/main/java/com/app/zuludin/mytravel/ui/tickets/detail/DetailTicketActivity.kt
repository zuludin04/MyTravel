package com.app.zuludin.mytravel.ui.tickets.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Rental
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.ui.tickets.detail.flight.DetailFlightFragment
import com.app.zuludin.mytravel.ui.tickets.detail.hotel.DetailHotelFragment
import com.app.zuludin.mytravel.ui.tickets.detail.rental.DetailRentalFragment
import com.app.zuludin.mytravel.ui.tickets.detail.train.DetailTrainFragment
import com.app.zuludin.mytravel.utils.addFragment
import com.app.zuludin.mytravel.utils.toolbarTitle
import kotlinx.android.synthetic.main.detail_ticket_activity.*

class DetailTicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_ticket_activity)

        val pageTitle = intent.getStringExtra(DETAIL_PAGE_TITLE)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupFragment(pageTitle)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupFragment(fragmentConst: String) {
        when (fragmentConst) {
            "Flight" -> {
                val flight: Flight = intent.getParcelableExtra(DETAIL_DATA)
                toolbarTitle("${flight.originCode} - ${flight.destinationCode}")
                initializeFragment(DetailFlightFragment.getInstance(flight))
            }
            "Hotel" -> {
                val hotel: Hotel = intent.getParcelableExtra(DETAIL_DATA)
                toolbarTitle(hotel.name.toString())
                initializeFragment(DetailHotelFragment.getInstance(hotel))
            }
            "Train" -> {
                val train: Train = intent.getParcelableExtra(DETAIL_DATA)
                toolbarTitle("${train.codeOrigin} - ${train.codeDestination}")
                initializeFragment(DetailTrainFragment.getInstance(train))
            }
            "Rental" -> {
                val rental: Rental = intent.getParcelableExtra(DETAIL_DATA)
                toolbarTitle(rental.car.toString())
                initializeFragment(DetailRentalFragment.getInstance(rental))
            }
        }
    }

    private fun initializeFragment(fragment: androidx.fragment.app.Fragment) {
        addFragment(fragment, R.id.frame_container)
    }

    companion object {
        const val DETAIL_PAGE_TITLE = "detailPageTitle"
        const val DETAIL_DATA = "detailData"
    }
}

package com.app.zuludin.mytravel.ui.tickets.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.CarRental
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.CarListViewHolder
import com.app.zuludin.mytravel.utils.DataProvider.flightTickets
import com.app.zuludin.mytravel.utils.DataProvider.hotelsData
import com.app.zuludin.mytravel.utils.DataProvider.rentalCarList
import com.app.zuludin.mytravel.utils.DataProvider.trainTickets
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.app.zuludin.mytravel.ui.tickets.detail.DetailTicketActivity
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.FlightTicketViewHolder
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.HotelListViewHolder
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.TrainTicketViewHolder
import com.app.zuludin.mytravel.utils.toolbarTitle
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.ticket_list_activity.*

class TicketListActivity : AppCompatActivity() {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(Flight::class, R.layout.item_flight_ticket) {
                FlightTicketViewHolder(it) { flight, icon ->
                    val intent = Intent(applicationContext, DetailTicketActivity::class.java)
                    val iconPair = Pair.create<View, String>(icon, getString(R.string.image_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TicketListActivity, iconPair)

                    intent.putExtra(DetailTicketActivity.DETAIL_PAGE_TITLE, "Flight")
                    intent.putExtra(DetailTicketActivity.DETAIL_DATA, flight)
                    startActivity(intent, options.toBundle())
                }
            }
            addViewHolder(Hotel::class, R.layout.item_hotel_list) {
                HotelListViewHolder(it) { hotel, imageTransition ->
                    val intent = Intent(applicationContext, DetailTicketActivity::class.java)
                    val imagePair =
                        Pair.create<View, String>(imageTransition, getString(R.string.image_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TicketListActivity, imagePair)

                    intent.putExtra(DetailTicketActivity.DETAIL_PAGE_TITLE, "Hotel")
                    intent.putExtra(DetailTicketActivity.DETAIL_DATA, hotel)
                    startActivity(intent, options.toBundle())
                }
            }
            addViewHolder(CarRental::class, R.layout.item_car_list) {
                CarListViewHolder(it) { rental, imageTransition ->
                    val intent = Intent(applicationContext, DetailTicketActivity::class.java)
                    val imagePair =
                        Pair.create<View, String>(imageTransition, getString(R.string.image_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TicketListActivity, imagePair)

                    intent.putExtra(DetailTicketActivity.DETAIL_PAGE_TITLE, "Rental")
                    intent.putExtra(DetailTicketActivity.DETAIL_DATA, rental)
                    startActivity(intent, options.toBundle())
                }
            }
            addViewHolder(Train::class, R.layout.item_train_ticket) {
                TrainTicketViewHolder(it) { train, textTransition ->
                    val intent = Intent(applicationContext, DetailTicketActivity::class.java)
                    val imagePair = Pair.create<View, String>(textTransition, getString(R.string.text_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TicketListActivity, imagePair)

                    intent.putExtra(DetailTicketActivity.DETAIL_PAGE_TITLE, "Train")
                    intent.putExtra(DetailTicketActivity.DETAIL_DATA, train)
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ticket_list_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = intent.getStringExtra(TITLE)
            subtitle = intent.getStringExtra(SUBTITLE)
            setDisplayHomeAsUpEnabled(true)
        }

        recycler_ticket.layoutManager = LinearLayoutManager(applicationContext)
        recycler_ticket.addItemDecoration(SpacingItemDecoration(16))
        recycler_ticket.setHasFixedSize(true)
        recycler_ticket.adapter = adapter

        adapter.set(ArrayList())

        when (intent.getStringExtra(LIST_TYPE)) {
            "Flight" -> {
                val flight: Flight = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle("${flight.originCity} - ${flight.destinationCity}", flight.originDate)
                adapter.insert(
                    flightTickets(
                        flight.airportOrigin.toString(),
                        flight.airportDestination.toString(),
                        flight.originCity.toString(),
                        flight.destinationCity.toString(),
                        flight.originCode.toString(),
                        flight.destinationCode.toString(),
                        flight.adultPassenger!!,
                        flight.childPassenger!!,
                        flight.infantPassenger!!,
                        flight.seatClass.toString(),
                        flight.originDate.toString(),
                        flight.destinationDate.toString()
                    )
                )
            }
            "Hotel" -> {
                val hotel: Hotel = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle(hotel.hotelCity.toString(), hotel.checkIn)
                adapter.insert(
                    hotelsData(
                        hotel.hotelCity.toString(),
                        hotel.hotelLocation.toString(),
                        hotel.checkIn.toString(),
                        hotel.checkOut.toString(),
                        hotel.stayDuration!!,
                        hotel.totalGuest.toString(),
                        hotel.totalRoom.toString()
                    )
                )
            }
            "Rental" -> {
                val rental: CarRental = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle(rental.rentalLocation.toString(), rental.startDate)
                adapter.insert(
                    rentalCarList(
                        rental.rentalDuration.toString(),
                        rental.pickupTime.toString(),
                        rental.startDate.toString(),
                        rental.finishDate.toString(),
                        rental.rentalLocation.toString()
                    )
                )
            }
            "Train" -> {
                val train: Train = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle("${train.stationOrigin} - ${train.stationDestination}", train.departureDate)
                adapter.insert(
                    trainTickets(
                        train.stationOrigin.toString(),
                        train.stationDestination.toString(),
                        train.cityOrigin.toString(),
                        train.cityDestination.toString(),
                        train.codeOrigin.toString(),
                        train.codeDestination.toString(),
                        train.adult!!,
                        train.child!!,
                        train.seatClass.toString(),
                        train.departureDate.toString()
                    )
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    companion object {
        const val LIST_TYPE = "listType"
        const val TICKET_DATA = "ticketData"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
    }
}

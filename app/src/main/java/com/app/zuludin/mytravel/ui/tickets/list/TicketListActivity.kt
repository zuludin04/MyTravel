package com.app.zuludin.mytravel.ui.tickets.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.data.model.remote.Rental
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.ui.tickets.detail.DetailTicketActivity
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.CarListViewHolder
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.FlightTicketViewHolder
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.HotelListViewHolder
import com.app.zuludin.mytravel.ui.tickets.list.viewholder.TrainTicketViewHolder
import com.app.zuludin.mytravel.utils.DataProvider.hotelsData
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.app.zuludin.mytravel.utils.ViewModelFactory
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
            addViewHolder(Rental::class, R.layout.item_car_list) {
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

    private val viewModel: TicketListViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(TicketListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ticket_list_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setupRecycler()
        setupTicketList()
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

    private fun setupRecycler() {
        recycler_ticket.layoutManager = LinearLayoutManager(applicationContext)
        recycler_ticket.addItemDecoration(SpacingItemDecoration(16))
        recycler_ticket.setHasFixedSize(true)
        recycler_ticket.adapter = adapter

        adapter.set(ArrayList())
    }

    private fun setupTicketList() {
        when (intent.getStringExtra(LIST_TYPE)) {
            "Flight" -> {
                val flight: Flight = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle("${flight.originCity} - ${flight.destinationCity}", flight.date)
                viewModel.getFlightTickets(
                    flight.airportOrigin.toString(),
                    flight.airportDestination.toString(),
                    flight.originCity.toString(),
                    flight.destinationCity.toString(),
                    flight.originCode.toString(),
                    flight.destinationCode.toString(),
                    flight.adult!!,
                    flight.child!!,
                    flight.infant!!,
                    flight.seatClass.toString(),
                    flight.date.toString()
                ).observe(this, Observer { flights ->
                    flights?.let {
                        adapter.insert(it)
                        recycler_ticket.adapter = adapter
                    }
                })
            }
            "Hotel" -> {
                val hotel: Hotel = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle(hotel.city.toString(), hotel.checkIn)
                adapter.insert(
                    hotelsData(
                        hotel.city.toString(),
                        hotel.checkIn.toString(),
                        hotel.checkOut.toString(),
                        hotel.duration!!,
                        hotel.guest.toString(),
                        hotel.room.toString()
                    )
                )
            }
            "Rental" -> {
                val rental: Rental = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle(rental.region.toString(), rental.startDate)
                viewModel.getRentalCars(
                    rental.duration.toString(),
                    rental.pickUpTime.toString(),
                    rental.startDate.toString(),
                    rental.finishDate.toString(),
                    rental.region.toString()
                ).observe(this, Observer { rentals ->
                    rentals?.let {
                        adapter.insert(it)
                        recycler_ticket.adapter = adapter
                    }
                })
            }
            "Train" -> {
                val train: Train = intent.getParcelableExtra(TICKET_DATA)
                toolbarTitle("${train.stationOrigin} - ${train.stationDestination}", train.date)
                viewModel.getTrainTickets(
                    train.stationOrigin.toString(),
                    train.stationDestination.toString(),
                    train.cityOrigin.toString(),
                    train.cityDestination.toString(),
                    train.codeOrigin.toString(),
                    train.codeDestination.toString(),
                    train.adult!!,
                    train.child!!,
                    train.seatClass.toString(),
                    train.date.toString()
                ).observe(this, Observer { trains ->
                    trains?.let {
                        adapter.insert(it)
                        recycler_ticket.adapter = adapter
                    }
                })
            }
        }
    }

    companion object {
        const val LIST_TYPE = "listType"
        const val TICKET_DATA = "ticketData"
    }
}

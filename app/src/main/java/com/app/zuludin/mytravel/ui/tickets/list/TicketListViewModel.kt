package com.app.zuludin.mytravel.ui.tickets.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.Flight
import com.app.zuludin.mytravel.data.model.remote.Rental
import com.app.zuludin.mytravel.data.model.remote.Train
import com.app.zuludin.mytravel.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TicketListViewModel(
    application: Application,
    private val contextProvider: CoroutineContextProvider
) : AndroidViewModel(application) {

    lateinit var flightTicketDataList: MutableLiveData<List<Flight>>
    lateinit var rentalCarDataList: MutableLiveData<List<Rental>>
    lateinit var traintTicketDataList: MutableLiveData<List<Train>>

    private val repository = TravelDataRepository(getApplication())

    fun getFlightTickets(
        airportOrigin: String,
        airportDestination: String,
        originCity: String,
        destinationCity: String,
        originCode: String,
        destinationCode: String,
        adult: Int,
        child: Int,
        infant: Int,
        seatClass: String,
        date: String
    ): LiveData<List<Flight>> {
        if (!::flightTicketDataList.isInitialized) {
            flightTicketDataList = MutableLiveData()

            GlobalScope.launch(contextProvider.main) {
                val flights: List<Flight> = repository.loadFlightTicket().flights
                for (i in flights.indices) {
                    val flight: Flight = flights[i]
                    flight.airportOrigin = airportOrigin
                    flight.airportDestination = airportDestination
                    flight.originCity = originCity
                    flight.destinationCity = destinationCity
                    flight.originCode = originCode
                    flight.destinationCode = destinationCode
                    flight.adult = adult
                    flight.child = child
                    flight.infant = infant
                    flight.seatClass = seatClass
                    flight.date = date
                }
                flightTicketDataList.value = flights
            }
        }
        return flightTicketDataList
    }

    fun getTrainTickets(
        stationOrigin: String,
        stationDestination: String,
        originCity: String,
        destinationCity: String,
        originCode: String,
        destinationCode: String,
        adult: Int,
        child: Int,
        seatClass: String,
        date: String
    ): LiveData<List<Train>> {
        if (!::traintTicketDataList.isInitialized) {
            traintTicketDataList = MutableLiveData()

            GlobalScope.launch(contextProvider.main) {
                val trains: List<Train> = repository.loadTrainTicket().trains
                for (i in trains.indices) {
                    val train: Train = trains[i]
                    train.stationOrigin = stationOrigin
                    train.stationDestination = stationDestination
                    train.cityOrigin = originCity
                    train.cityDestination = destinationCity
                    train.codeOrigin = originCode
                    train.codeDestination = destinationCode
                    train.adult = adult
                    train.child = child
                    train.seatClass = seatClass
                    train.date = date
                }
                traintTicketDataList.value = trains
            }
        }
        return traintTicketDataList
    }

    fun getRentalCars(
        duration: String,
        pickup: String,
        start: String,
        finish: String,
        region: String
    ): LiveData<List<Rental>> {
        if (!::rentalCarDataList.isInitialized) {
            rentalCarDataList = MutableLiveData()
            GlobalScope.launch(contextProvider.main) {
                val rentals: List<Rental> = repository.loadRentalCarList().rentals
                for (i in rentals.indices) {
                    val rental: Rental = rentals[i]
                    rental.duration = duration
                    rental.pickUpTime = pickup
                    rental.startDate = start
                    rental.finishDate = finish
                    rental.region = region
                }
                rentalCarDataList.value = rentals
            }
        }
        return rentalCarDataList
    }
}
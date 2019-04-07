package com.app.zuludin.mytravel.ui.tickets.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.TestContextProvider
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.*
import com.google.common.collect.Lists
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TicketListViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var flightData: MutableLiveData<List<Flight>>

    @Mock
    private lateinit var trainData: MutableLiveData<List<Train>>

    @Mock
    private lateinit var rentalData: MutableLiveData<List<Rental>>

    @Mock
    private lateinit var hotelData: MutableLiveData<List<Hotel>>

    @Mock
    private lateinit var repository: TravelDataRepository

    private lateinit var viewModel: TicketListViewModel

    private lateinit var flightResult: FlightList

    private lateinit var trainResult: TrainList

    private lateinit var rentalResult: RentalList

    private lateinit var hotelResult: HotelList

    @Before
    fun setupTicketDataTest() {
        MockitoAnnotations.initMocks(this)

        viewModel = TicketListViewModel(application, TestContextProvider())

        val flights: List<Flight> = Lists.newArrayList(
            Flight(dataId = 1, airline = "Garuda Indonesia"),
            Flight(dataId = 2, airline = "Air Asia")
        )
        flightResult = FlightList(flights)

        val trains: List<Train> = Lists.newArrayList(
            Train(dataId = 1, argo = "Argo Parahyangan"),
            Train(dataId = 2, argo = "Argo Parabumi")
        )
        trainResult = TrainList(trains)

        val rentals: List<Rental> = Lists.newArrayList(
            Rental(dataId = 1, car = "Avanza"),
            Rental(dataId = 2, car = "Brio")
        )
        rentalResult = RentalList(rentals)

        val hotels: List<Hotel> = Lists.newArrayList(
            Hotel(dataId = 1, name = "Hotel Jaya"),
            Hotel(dataId = 2, name = "Hotel Jayabaya")
        )
        hotelResult = HotelList(hotels)
    }

    @Test
    fun `load flight ticket list`() {
        viewModel.flightTicketDataList = flightData

        runBlocking {
            Mockito.`when`(repository.loadFlightTicket()).thenReturn(flightResult)
            Mockito.`when`(viewModel.flightTicketDataList.value).thenReturn(flightResult.flights)

            viewModel.getFlightTickets(
                "Soekarno-Hatta",
                "Ngurah Rai",
                "Tangerang",
                "Bali",
                "SKH",
                "NGR",
                1,
                0,
                0,
                "executive",
                "03 Mar 2019"
            )

            assertEquals(2, viewModel.flightTicketDataList.value?.size)
        }
    }

    @Test
    fun `load train ticket list`() {
        viewModel.trainTicketDataList = trainData

        runBlocking {
            Mockito.`when`(repository.loadTrainTicket()).thenReturn(trainResult)
            Mockito.`when`(viewModel.trainTicketDataList.value).thenReturn(trainResult.trains)

            viewModel.getTrainTickets(
                "Gambir",
                "Bandung",
                "Jakarta",
                "Bandung",
                "GMB",
                "BD",
                1,
                0,
                "Economy",
                "05 Mei 2018"
            )

            assertEquals(2, viewModel.trainTicketDataList.value?.size)
        }
    }

    @Test
    fun `get car rental list test`() {
        viewModel.rentalCarDataList = rentalData

        runBlocking {
            Mockito.`when`(repository.loadRentalCarList()).thenReturn(rentalResult)
            Mockito.`when`(viewModel.rentalCarDataList.value).thenReturn(rentalResult.rentals)

            viewModel.getRentalCars(
                "2 days",
                "13.00",
                "08 Mar 2019",
                "10 Mar 2019",
                "Bogor"
            )

            assertEquals(2, viewModel.rentalCarDataList.value?.size)
        }
    }

    @Test
    fun `load hotel list test`() {
        viewModel.hotelDataList = hotelData

        runBlocking {
            Mockito.`when`(repository.loadHotelList()).thenReturn(hotelResult)
            Mockito.`when`(viewModel.hotelDataList.value).thenReturn(hotelResult.hotels)

            viewModel.getHotels(
                "Bogor",
                "10 Oct 2018",
                "11 Oct 2018",
                1,
                "2",
                "1"
            )

            assertEquals(2, viewModel.hotelDataList.value?.size)
        }
    }
}
package com.app.zuludin.mytravel.data

import android.content.Context
import com.app.zuludin.mytravel.data.model.remote.*
import com.app.zuludin.mytravel.data.source.TravelRemoteSource

class TravelDataRepository(
    private val context: Context
) : TravelDataSource {
    private val remoteSource = TravelRemoteSource()

    override suspend fun loadMainExploreData(): List<ExploreList> = remoteSource.loadMainExploreData(context)

    override suspend fun loadExploreByCategory(category: String): List<TravelExplore> =
        remoteSource.loadCategoryExplore(context, category)

    override suspend fun loadDetailExplore(id: Int): TravelExplore =
            remoteSource.loadDetailExplore(context, id)

    override suspend fun loadFlightTicket(): FlightList = remoteSource.loadFlightTicketList(context)

    override suspend fun loadRentalCarList(): RentalList = remoteSource.loadCarRentals(context)

    override suspend fun loadTrainTicket(): TrainList = remoteSource.loadTrainTicketList(context)

    override suspend fun loadHotelList(): HotelList = remoteSource.loadHotelList(context)
}
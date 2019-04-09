package com.app.zuludin.mytravel.data

import android.content.Context
import com.app.zuludin.mytravel.data.model.local.Favourite
import com.app.zuludin.mytravel.data.model.remote.*
import com.app.zuludin.mytravel.data.source.local.TravelLocalCallback
import com.app.zuludin.mytravel.data.source.local.TravelLocalImpl
import com.app.zuludin.mytravel.data.source.remote.TravelRemoteSource

class TravelDataRepository(
    private val context: Context
) : TravelDataSource {

    private val remoteSource = TravelRemoteSource()
    private val localSource = TravelLocalImpl(context)

    override suspend fun loadMainExploreData(): List<ExploreList> = remoteSource.loadMainExploreData(context)

    override suspend fun loadExploreByCategory(category: String): List<TravelExplore> =
        remoteSource.loadCategoryExplore(context, category)

    override suspend fun loadDetailExplore(id: Int): TravelExplore =
            remoteSource.loadDetailExplore(context, id)

    override suspend fun loadFlightTicket(): FlightList = remoteSource.loadFlightTicketList(context)

    override suspend fun loadRentalCarList(): RentalList = remoteSource.loadCarRentals(context)

    override suspend fun loadTrainTicket(): TrainList = remoteSource.loadTrainTicketList(context)

    override suspend fun loadHotelList(): HotelList = remoteSource.loadHotelList(context)

    override fun loadFavouritePlaces(callback: TravelDataSource.GetFavouritePlacesCallback) {
        localSource.loadFavouritePlaces(object : TravelLocalCallback.GetFavouritePlacesCallback {
            override fun onFavourites(favourites: List<Favourite>) {
                callback.onFavourites(favourites)
            }

            override fun onEmpty() {
                callback.onEmpty()
            }
        })
    }

    override fun isPlaceFavourite(id: String): Boolean = localSource.isPlaceFavourite(id)

    override fun insertFavourite(favourite: Favourite) {
        localSource.insertFavourite(favourite)
    }

    override fun deleteFavourite(id: String) {
        localSource.deleteFavourite(id)
    }
}
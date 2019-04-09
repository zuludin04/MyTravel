package com.app.zuludin.mytravel.data

import com.app.zuludin.mytravel.data.model.local.Favourite
import com.app.zuludin.mytravel.data.model.remote.*

interface TravelDataSource {

    suspend fun loadMainExploreData(): List<ExploreList>

    suspend fun loadExploreByCategory(category: String): List<TravelExplore>

    suspend fun loadDetailExplore(id: Int): TravelExplore

    suspend fun loadFlightTicket(): FlightList

    suspend fun loadTrainTicket(): TrainList

    suspend fun loadRentalCarList(): RentalList

    suspend fun loadHotelList(): HotelList

    fun loadFavouritePlaces(callback: GetFavouritePlacesCallback)

    fun isPlaceFavourite(id: String): Boolean

    fun insertFavourite(favourite: Favourite)

    fun deleteFavourite(id: String)

    interface GetFavouritePlacesCallback {
        fun onFavourites(favourites: List<Favourite>)
        fun onEmpty()
    }
}
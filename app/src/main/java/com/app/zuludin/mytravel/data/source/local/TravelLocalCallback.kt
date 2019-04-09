package com.app.zuludin.mytravel.data.source.local

import com.app.zuludin.mytravel.data.model.local.Favourite

interface TravelLocalCallback {

    fun loadFavouritePlaces(callback: GetFavouritePlacesCallback)

    fun isPlaceFavourite(id: String): Boolean

    fun insertFavourite(favourite: Favourite)

    fun deleteFavourite(id: String)

    interface GetFavouritePlacesCallback {
        fun onFavourites(favourites: List<Favourite>)
        fun onEmpty()
    }
}
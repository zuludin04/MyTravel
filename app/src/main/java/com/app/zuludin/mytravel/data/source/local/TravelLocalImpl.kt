package com.app.zuludin.mytravel.data.source.local

import android.content.Context
import com.app.zuludin.mytravel.data.model.local.Favourite
import com.app.zuludin.mytravel.utils.travelDb
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TravelLocalImpl(private val context: Context): TravelLocalCallback {

    override fun loadFavouritePlaces(callback: TravelLocalCallback.GetFavouritePlacesCallback) {
        context.travelDb.use {
            val result = select(Favourite.FAVORITE_TABLE)
            val favourites = result.parseList(classParser<Favourite>())

            if (!favourites.isEmpty()) {
                callback.onFavourites(favourites)
            } else {
                callback.onEmpty()
            }
        }
    }

    override fun insertFavourite(favourite: Favourite) {
        context.travelDb.use {
            insert(Favourite.FAVORITE_TABLE,
                Favourite.DATA_ID to favourite.dataId,
                Favourite.NAME to favourite.name,
                Favourite.THUMBNAIL to favourite.thumbnail,
                Favourite.CATEGORY to favourite.category)
        }
    }

    override fun isPlaceFavourite(id: String): Boolean {
        var isFavourite = false
        context.travelDb.use {
            val result = select(Favourite.FAVORITE_TABLE).whereArgs("(dataId) = {id}", "id" to id)
            val parser = result.parseList(classParser<Favourite>())
            isFavourite = !parser.isEmpty()
        }
        return isFavourite
    }

    override fun deleteFavourite(id: String) {
        context.travelDb.use {
            delete(Favourite.FAVORITE_TABLE, "dataId = {id}", "id" to id)
        }
    }
}
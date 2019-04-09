package com.app.zuludin.mytravel.ui.main.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.TravelDataSource
import com.app.zuludin.mytravel.data.model.local.Favourite

class MainFavouriteViewModel(application: Application): AndroidViewModel(application) {

    lateinit var favouriteExploreData: MutableLiveData<List<Favourite>>

    private val repository = TravelDataRepository(getApplication())

    fun loadFavouritePlaces(): LiveData<List<Favourite>> {
        favouriteExploreData = MutableLiveData()
        repository.loadFavouritePlaces(object : TravelDataSource.GetFavouritePlacesCallback {
            override fun onFavourites(favourites: List<Favourite>) {
                favouriteExploreData.value = favourites
            }

            override fun onEmpty() {

            }
        })
        return favouriteExploreData
    }
}
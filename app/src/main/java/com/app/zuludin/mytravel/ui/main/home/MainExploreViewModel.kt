package com.app.zuludin.mytravel.ui.main.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.model.remote.CurrentWeather
import com.app.zuludin.mytravel.data.model.remote.Explore
import com.app.zuludin.mytravel.data.source.TravelRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainExploreViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var exploreDataList: MutableLiveData<List<Explore>>

    private lateinit var currentWeather: MutableLiveData<CurrentWeather>

    private val remoteDataSource: TravelRemoteSource = TravelRemoteSource()

    fun getExplores(): LiveData<List<Explore>> {
        if (!::exploreDataList.isInitialized) {
            exploreDataList = MutableLiveData()
            loadTravelList()
        }

        return exploreDataList
    }

    fun getCurrentWeather(): LiveData<CurrentWeather> {
        if (!::currentWeather.isInitialized) {
            currentWeather = MutableLiveData()
            loadCurrentWeather()
        }
        return currentWeather
    }

    private fun loadTravelList() {
        GlobalScope.launch(Dispatchers.Main) {
            exploreDataList.value = remoteDataSource.getTravelDataList()
        }
    }

    private fun loadCurrentWeather() {
        GlobalScope.launch(Dispatchers.Main) {
            val weather = remoteDataSource.getCurrentWeatherAsync().await().body()
            currentWeather.value = weather
        }
    }
}
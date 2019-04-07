package com.app.zuludin.mytravel.ui.main.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainExploreViewModel(
    application: Application,
    private val contextProvider: CoroutineContextProvider
) : AndroidViewModel(application) {

    lateinit var travelExploreDataList: MutableLiveData<List<ExploreList>>

    private val repository = TravelDataRepository(getApplication())

    fun getExplores(): LiveData<List<ExploreList>> {
        if (!::travelExploreDataList.isInitialized) {
            travelExploreDataList = MutableLiveData()
            loadExploreList()
        }
        return travelExploreDataList
    }

    fun loadExploreList() {
        GlobalScope.launch(contextProvider.main) {
            delay(3000)

            val list: List<ExploreList> = repository.loadMainExploreData()
            travelExploreDataList.value = list
        }
    }
}
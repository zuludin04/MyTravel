package com.app.zuludin.mytravel.ui.explore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExploreDetailViewModel(
    application: Application,
    private val contextProvider: CoroutineContextProvider
) : AndroidViewModel(application) {

    lateinit var exploreDetailData: MutableLiveData<TravelExplore>

    private val repository = TravelDataRepository(getApplication())

    fun getDetail(id: Int): LiveData<TravelExplore> {
        if (!::exploreDetailData.isInitialized) {
            exploreDetailData = MutableLiveData()
            loadDetailExplore(id)
        }
        return exploreDetailData
    }

    fun loadDetailExplore(id: Int) {
        GlobalScope.launch(contextProvider.main) {
            val explore = repository.loadDetailExplore(id)
            exploreDetailData.value = explore
        }
    }
}
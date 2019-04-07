package com.app.zuludin.mytravel.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryViewModel(
    application: Application,
    private val contextProvider: CoroutineContextProvider
) : AndroidViewModel(application) {

    lateinit var exploreByCategoryDataList: MutableLiveData<List<TravelExplore>>

    private val repository = TravelDataRepository(getApplication())

    fun getExploreByCategory(category: String): LiveData<List<TravelExplore>> {
        if (!::exploreByCategoryDataList.isInitialized) {
            exploreByCategoryDataList = MutableLiveData()
            loadCategoryExplores(category)
        }
        return exploreByCategoryDataList
    }

    fun loadCategoryExplores(category: String) {
        GlobalScope.launch(contextProvider.main) {
            val list: List<TravelExplore> = repository.loadExploreByCategory(category)
            exploreByCategoryDataList.value = list
        }
    }
}
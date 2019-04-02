package com.app.zuludin.mytravel.data

import com.app.zuludin.mytravel.data.model.local.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelExplore

interface TravelDataSource {

    suspend fun loadMainExploreData(): List<ExploreList>

    suspend fun loadExploreByCategory(category: String): List<TravelExplore>

    interface ExploreDataListCallback {
        fun onShowData(explores: List<ExploreList>)
        fun onEmpty()
    }

    interface CategoryExploreCallback {
        fun onShowData(explores: List<TravelExplore>)
        fun onEmpty()
    }
}
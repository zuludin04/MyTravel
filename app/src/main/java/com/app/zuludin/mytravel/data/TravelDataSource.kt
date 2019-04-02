package com.app.zuludin.mytravel.data

import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelExplore

interface TravelDataSource {

    suspend fun loadMainExploreData(): List<ExploreList>

    suspend fun loadExploreByCategory(category: String): List<TravelExplore>

    suspend fun loadDetailExplore(id: Int): TravelExplore
}
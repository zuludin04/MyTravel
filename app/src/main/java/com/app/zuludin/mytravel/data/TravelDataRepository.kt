package com.app.zuludin.mytravel.data

import android.content.Context
import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.data.source.TravelRemoteSource

class TravelDataRepository(
    private val context: Context
) : TravelDataSource {
    private val remoteSource = TravelRemoteSource()

    override suspend fun loadMainExploreData(): List<ExploreList> = remoteSource.loadMainExploreData(context)

    override suspend fun loadExploreByCategory(category: String): List<TravelExplore> =
        remoteSource.loadCategoryExplore(context, category)

    override suspend fun loadDetailExplore(id: Int): TravelExplore =
            remoteSource.loadDetailExplore(context, id)
}
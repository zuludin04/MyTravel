package com.app.zuludin.mytravel.data.source

import android.content.Context
import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelData
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.utils.JsonUtils
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class TravelRemoteSource : TravelRemoteCallback {

//    private val database = FirebaseDatabase.getInstance().reference

    override fun loadUserTransaction(callback: TravelRemoteCallback.TransactionCallback) {
       /* val list: MutableList<Transaction> = mutableListOf()
        val query: Query = database.child("Transaction")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    val transaction = snapshot.getValue(Transaction::class.java)
//                    transaction?.id = snapshot.key
                    if (transaction != null) {
                        list.add(transaction)
                        callback.onTransactionList(list)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback.onError(error.message)
            }
        })*/
    }

    suspend fun loadMainExploreData(context: Context): List<ExploreList> = withContext(Dispatchers.Default) {
        val data = JsonUtils.readJsonFile(context, "explore.json")
        val gson = GsonBuilder().setPrettyPrinting().create()
        val travelData: TravelData = gson.fromJson(data, TravelData::class.java)

        val list: MutableList<ExploreList> = mutableListOf()

        val random = Random(travelData.explore.size)

        val recommendations: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            recommendations.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        val topSpot: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            topSpot.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        val recent: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            recent.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        list.add(ExploreList(recommendations, "Recommendations"))
        list.add(ExploreList(topSpot, "Top Spots"))
        list.add(ExploreList(recent, "Recent Place"))

        list
    }

    suspend fun loadCategoryExplore(context: Context, category: String): List<TravelExplore> =
        withContext(Dispatchers.Default) {
            val data = JsonUtils.readJsonFile(context, "explore.json")
            val gson = GsonBuilder().setPrettyPrinting().create()
            val travelData: TravelData = gson.fromJson(data, TravelData::class.java)

            val explores: MutableList<TravelExplore> = mutableListOf()
            explores.addAll(travelData.explore.filter { it.category == category }.toList())

            explores
        }

    suspend fun loadDetailExplore(context: Context, id: Int): TravelExplore =
            withContext(Dispatchers.Default) {
                val data = JsonUtils.readJsonFile(context, "explore.json")
                val gson = GsonBuilder().setPrettyPrinting().create()
                val travelData: TravelData = gson.fromJson(data, TravelData::class.java)
                val dataId = id.minus(1)

                val explore: TravelExplore = travelData.explore[dataId]
                explore
            }
}
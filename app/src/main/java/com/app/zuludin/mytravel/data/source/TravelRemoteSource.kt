package com.app.zuludin.mytravel.data.source

import com.app.zuludin.mytravel.BuildConfig
import com.app.zuludin.mytravel.data.model.remote.*
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.utils.DataProvider.exploresData
import com.app.zuludin.mytravel.utils.DataProvider.foodsData
import com.app.zuludin.mytravel.utils.WeatherClient
import com.google.firebase.database.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TravelRemoteSource : TravelRemoteCallback {

    private val service = WeatherClient.create()
    private val database = FirebaseDatabase.getInstance().reference

    override fun loadUserTransaction(callback: TravelRemoteCallback.TransactionCallback) {
        val list: MutableList<Transaction> = mutableListOf()
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
        })
    }

    suspend fun getTravelDataList(): List<Explore> =
        withContext(Dispatchers.Default) {
            exploresData()
        }

    suspend fun getFoodDataList(): List<Food> = withContext(Dispatchers.Default) {
        foodsData()
    }

    /*suspend fun getHotelDataList(): List<Hotel> =
        withContext(Dispatchers.Default) {
            hotelsData()
        }

    suspend fun getFlightTicketList(): List<Flight> =
        withContext(Dispatchers.Default) {
            flightTickets()
        }

    suspend fun getTrainTicketList(): List<Train> =
        withContext(Dispatchers.Default) {
            trainTickets()
        }

    suspend fun getRentalCarList(): List<CarRental> =
        withContext(Dispatchers.Default) {
            rentalCarList()
        }*/

    fun getForecastWeatherAsync(): Deferred<Response<ForecastWeather>> =
        service.getForecastWeatherAsync(BuildConfig.TSDB_API_KEY, "Jakarta", "7")

    fun getCurrentWeatherAsync(): Deferred<Response<CurrentWeather>> =
        service.getCurrentWeatherAsync(BuildConfig.TSDB_API_KEY, "Jakarta")
}
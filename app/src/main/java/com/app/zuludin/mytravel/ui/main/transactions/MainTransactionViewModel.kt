package com.app.zuludin.mytravel.ui.main.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.data.source.remote.TravelRemoteCallback
import com.app.zuludin.mytravel.data.source.remote.TravelRemoteSource

class MainTransactionViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var transactionDataList: MutableLiveData<List<Transaction>>

    private val repository: TravelRemoteSource =
        TravelRemoteSource()

    fun getTransactions(): LiveData<List<Transaction>> {
        if (!::transactionDataList.isInitialized) {
            transactionDataList = MutableLiveData()
            loadTransactionList()
        }
        return transactionDataList
    }

    private fun loadTransactionList() {
        repository.loadUserTransaction(object : TravelRemoteCallback.TransactionCallback {
            override fun onTransactionList(transactions: List<Transaction>) {
                transactionDataList.value = transactions
            }

            override fun onError(message: String) {

            }
        })
    }

}
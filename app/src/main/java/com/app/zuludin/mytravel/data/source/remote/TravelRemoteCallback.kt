package com.app.zuludin.mytravel.data.source.remote

import com.app.zuludin.mytravel.data.model.remote.Transaction

interface TravelRemoteCallback {

    fun loadUserTransaction(callback: TransactionCallback)

    interface TransactionCallback {
        fun onTransactionList(transactions: List<Transaction>)
        fun onError(message: String)
    }
}
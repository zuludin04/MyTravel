package com.app.zuludin.mytravel.data.source

import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.google.firebase.database.*

class TravelRemoteSource : TravelRemoteCallback {

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
}
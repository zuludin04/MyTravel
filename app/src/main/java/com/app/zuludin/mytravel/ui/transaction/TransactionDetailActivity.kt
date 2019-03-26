package com.app.zuludin.mytravel.ui.transaction

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.transaction_detail_activity.*

class TransactionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transaction_detail_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Transaction"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val database = FirebaseDatabase.getInstance().reference
        val transactionRef = database.child("Transaction")
        var dataTransaction = Transaction()

        val key = intent.getStringExtra("DATA_KEY")

        transactionRef.child(key).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapthot: DataSnapshot) {
                if (snapthot.exists()) {
                    val transaction = snapthot.getValue(Transaction::class.java)
                    dataTransaction = transaction!!
                    transaction_book.text = transaction.book
                    transaction_service.text = transaction.service
                    transaction_duration.text = transaction.duration
                    transaction_city.text = transaction.city
                    transaction_type.text = transaction.type
                    transaction_date.text = transaction.date
                    transaction_price.text = transaction.price

                    if (transaction.status == "Finish") {
                        price.visibility = View.GONE
                        confirm_payment_button.visibility = View.GONE
                        cancel_payment_button.text = "Remove this Transaction"
                        ticket_code.visibility = View.VISIBLE
                    }
                }
            }
        })

        confirm_payment_button.setOnClickListener {
            progress_layout.visibility = View.VISIBLE

            val newTransaction = Transaction(
                book = dataTransaction.book,
                city = dataTransaction.city,
                price = dataTransaction.price,
                service = dataTransaction.service,
                date = dataTransaction.date,
                duration = dataTransaction.duration,
                method = dataTransaction.method,
                type = dataTransaction.type,
                adult = dataTransaction.adult,
                child = dataTransaction.child,
                infant = dataTransaction.infant,
                status = "Finish"
            )

            val postTransaction = newTransaction.toMap()
            val update = HashMap<String, Any>()
            update["/Transaction/$key"] = postTransaction

            database.updateChildren(update).addOnSuccessListener {
                progress_layout.visibility = View.GONE

                price.visibility = View.GONE
                confirm_payment_button.visibility = View.GONE
                cancel_payment_button.text = "Remove this Transaction"
                ticket_code.visibility = View.VISIBLE

                setResult(Activity.RESULT_OK)
            }
        }

        cancel_payment_button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure want to cancel this transaction?")
            builder.setPositiveButton("OK") { _, _ ->
                transactionRef.child(key).removeValue()
                setResult(Activity.RESULT_OK)
                onBackPressed()
            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }
}

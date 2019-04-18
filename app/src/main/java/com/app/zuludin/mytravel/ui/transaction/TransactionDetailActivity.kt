package com.app.zuludin.mytravel.ui.transaction

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.utils.begone
import com.app.zuludin.mytravel.utils.visible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.transaction_detail_activity.*

class TransactionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transaction_detail_activity)

        setupToolbar()

//        loadTransactionData()
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
                    virtual_code.text = virtualAccount(transaction.code!!)

                    if (transaction.status == "Finish") {
                        price.begone()
                        confirm_payment_button.begone()
                        virtual_account.text = virtualAccount(transaction.code!!)
                        cancel_payment_button.text = "Remove this Transaction"
                        ticket_code.visible()
                        code.begone()
                    }
                }
            }
        })

//        buttonListener()
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
                code = dataTransaction.code,
                status = "Finish"
            )

            val postTransaction = newTransaction.toMap()
            val update = HashMap<String, Any>()
            update["/Transaction/$key"] = postTransaction

            database.updateChildren(update).addOnSuccessListener {
                progress_layout.begone()

                price.begone()
                confirm_payment_button.begone()
                code.begone()
                cancel_payment_button.text = "Remove this Transaction"
                ticket_code.visible()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Transaction"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadTransactionData() {

    }

    private fun virtualAccount(code: String): String {
        val builder = StringBuilder(code)
        builder.insert(4, " ")
        return builder.toString()
    }
}

package com.app.zuludin.mytravel.ui.tickets.review

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.app.zuludin.mylibrary.dialog.ContactDialogFragment
import com.app.zuludin.mylibrary.dialog.PassengerDataDialogFragment
import com.app.zuludin.mylibrary.dialog.listener.ContactInputListener
import com.app.zuludin.mylibrary.dialog.listener.PassengerInputListener
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Passenger
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.list.PaymentListActivity
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.review_ticket_activity.*

class ReviewTicketActivity : AppCompatActivity(), ContactInputListener, PassengerClickListener {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(Passenger::class, R.layout.item_passenger) {
                PassengerViewHolder(
                    it,
                    this@ReviewTicketActivity
                )
            }
        }
    }

    private lateinit var transaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_ticket_activity)

        transaction = intent.getParcelableExtra("Transaction")

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Review"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter.set(ArrayList())

        review_book.text = transaction.book
        review_city.text = transaction.city
        review_price.text = transaction.price
        review_service.text = transaction.service
        review_date.text = transaction.date
        review_duration.text = transaction.duration

        setupPassengerRecycler()

        review_add_contact.setOnClickListener {
            val dialog = ContactDialogFragment.getInstance(this)
            dialog.show(supportFragmentManager, "Dialog")
        }

        confirm_button.setOnClickListener {
            val intent = Intent(applicationContext, PaymentListActivity::class.java)
            intent.putExtra(PaymentListActivity.TRANSACTION_DATA, transaction)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }

    override fun onContact(name: String) {
        review_add_contact.text = name
    }

    override fun onClick(position: Int, type: String) {
        val dialog = PassengerDataDialogFragment.getInstance(position, type, object : PassengerInputListener {
            override fun onPassenger(name: String, position: Int) {
                val passenger = Passenger(name, type)
                adapter.replace(passenger, position)
            }
        })
        dialog.show(supportFragmentManager, "Dialog")
    }

    private fun setupPassengerRecycler() {
        recycler_passenger.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            isNestedScrollingEnabled = false
            adapter = this@ReviewTicketActivity.adapter
        }

        val passengers: MutableList<Passenger> = mutableListOf()

        if (transaction.adult == 0 && transaction.child == 0 && transaction.infant == 0) {
            header_passenger.visibility = View.GONE
            recycler_passenger.visibility = View.GONE
        } else {
            if (transaction.adult!! > 0) {
                for (i in 1..transaction.adult!!) {
                    passengers.add(Passenger("Adult", "Adult"))
                }
            }

            if (transaction.child!! > 0) {
                for (i in 1..transaction.child!!) {
                    passengers.add(Passenger("Child", "Child"))
                }
            }

            if (transaction.infant!! > 0) {
                for (i in 1..transaction.infant!!) {
                    passengers.add(Passenger("Infant", "Infant"))
                }
            }
            adapter.insert(passengers)
        }
    }
}

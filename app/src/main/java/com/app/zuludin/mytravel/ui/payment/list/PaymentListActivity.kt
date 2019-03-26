package com.app.zuludin.mytravel.ui.payment.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Payment
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.finish.PaymentActivity
import kotlinx.android.synthetic.main.payment_list_activity.*

class PaymentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_list_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Payment"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        val transaction: Transaction = intent.getParcelableExtra(TRANSACTION_DATA)

        transaction_main_information.text = transaction.book
        transaction_date.text = transaction.date
        transaction_price.text = transaction.price

        recycler_payment.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = PaymentListAdapter(paymentTypeList()) {
                val intent =
                    Intent(applicationContext, PaymentActivity::class.java)
                intent.putExtra("PaymentType", it.name)
                intent.putExtra(TRANSACTION_DATA, transaction)
                startActivity(intent)
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            }
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

    private fun paymentTypeList(): List<Payment> {
        val list: MutableList<Payment> = mutableListOf()

        val name = resources.getStringArray(R.array.payment_name)
        val description = resources.getStringArray(R.array.payment_description)
        val icon = resources.obtainTypedArray(R.array.payment_icon)

        for (i in name.indices) {
            list.add(Payment(icon.getResourceId(i, 0), name[i], description[i]))
        }

        icon.recycle()

        return list
    }

    companion object {
        const val TRANSACTION_DATA = "transactionData"
    }
}

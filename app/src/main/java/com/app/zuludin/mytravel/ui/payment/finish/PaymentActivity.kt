package com.app.zuludin.mytravel.ui.payment.finish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.payment.finish.select.PaymentSelectFragment
import com.app.zuludin.mytravel.ui.payment.list.PaymentListActivity
import com.app.zuludin.mytravel.utils.addFragment
import com.app.zuludin.mytravel.utils.toolbarTitle
import kotlinx.android.synthetic.main.payment_activity.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_activity)

        val type = intent.getStringExtra("PaymentType")
        val transaction: Transaction = intent.getParcelableExtra(PaymentListActivity.TRANSACTION_DATA)

        setSupportActionBar(toolbar)
        toolbarTitle(type, "${transaction.book}")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            addFragment(PaymentSelectFragment.getInstance(type, transaction), R.id.frame_container)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }
}

package com.app.zuludin.mytravel.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.ui.main.booking.MainBookingFragment
import com.app.zuludin.mytravel.ui.main.home.MainExploreFragment
import com.app.zuludin.mytravel.ui.main.transactions.MainTransactionsFragment
import com.app.zuludin.mytravel.utils.addFragment
import com.app.zuludin.mytravel.utils.replaceFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            addFragment(MainExploreFragment(), R.id.frame_container)
        }

        bottom_navigation.setNavigationChangeListener { view, position ->
            when (position) {
                0 -> replaceFragment(MainExploreFragment(), R.id.frame_container)
                1 -> replaceFragment(MainTransactionsFragment(), R.id.frame_container)
                2 -> replaceFragment(MainBookingFragment(), R.id.frame_container)
            }
        }
    }
}

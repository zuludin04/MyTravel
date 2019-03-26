package com.app.zuludin.mytravel.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.ui.main.home.MainExploreFragment
import com.app.zuludin.mytravel.ui.main.transactions.MainTransactionsFragment
import com.app.zuludin.mytravel.utils.replaceFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_explore -> replaceFragment(MainExploreFragment(), R.id.frame_container)
                R.id.bottom_transaction -> replaceFragment(MainTransactionsFragment(), R.id.frame_container)
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.bottom_explore
    }
}

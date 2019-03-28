package com.app.zuludin.mytravel.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.ui.main.home.MainExploreFragment
import com.app.zuludin.mytravel.utils.addFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            addFragment(MainExploreFragment(), R.id.frame_container)
        }
    }
}

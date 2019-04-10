package com.app.zuludin.mytravel.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.ui.main.favorite.MainFavoriteFragment
import com.app.zuludin.mytravel.ui.main.home.MainExploreFragment
import com.app.zuludin.mytravel.ui.main.settings.MainSettingsFragment
import com.app.zuludin.mytravel.ui.main.transactions.MainTransactionsFragment
import com.app.zuludin.mytravel.utils.FragmentStateHelper
import com.app.zuludin.mytravel.utils.addFragment
import com.app.zuludin.mytravel.utils.replaceFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var stateHelper: FragmentStateHelper
    private val fragments = mutableMapOf<Int, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)
        stateHelper = FragmentStateHelper(supportFragmentManager)

        state(savedInstanceState)
        bottomNavSetup()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveCurrentState()
        outState.putBundle(STATE_HELPER, stateHelper.saveHelperSate())
        super.onSaveInstanceState(outState)
    }

    private fun state(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(MainExploreFragment(), R.id.frame_container)
        } else {
            val helperState = savedInstanceState.getBundle(STATE_HELPER)
            stateHelper.restoreHelperState(helperState)
        }
    }

    private fun bottomNavSetup() {
        bottom_navigation.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> {
                    val fragment = fragments[position] ?: MainExploreFragment()
                    fragments[position] = fragment
                    saveCurrentState()
                    stateHelper.restoreState(fragment, position)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commitNowAllowingStateLoss()
                }
                1 -> {
                    replaceFragment(MainTransactionsFragment(), R.id.frame_container)
                }
                2 -> {
                    replaceFragment(MainFavoriteFragment(), R.id.frame_container)
                }
                3 -> {
                    replaceFragment(MainSettingsFragment(), R.id.frame_container)
                }
            }
        }
    }

    private fun saveCurrentState() {
        fragments[bottom_navigation.currentActiveItemPosition]?.let {
            stateHelper.saveState(it, bottom_navigation.currentActiveItemPosition)
        }
    }

    companion object {
        private const val STATE_HELPER = "helper"
    }
}

package com.app.zuludin.mytravel.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentStateHelper(private val fragmentManager: FragmentManager) {

    private val fragmentSavedStates = mutableMapOf<Int, Fragment.SavedState?>()

    fun restoreState(fragment: Fragment, key: Int) {
        fragmentSavedStates[key]?.let {
            if (!fragment.isAdded) {
                fragment.setInitialSavedState(it)
            }
        }
    }

    fun saveState(fragment: Fragment, key: Int) {
        if (fragment.isAdded) {
            fragmentSavedStates[key] = fragmentManager.saveFragmentInstanceState(fragment)
        }
    }

    fun saveHelperSate(): Bundle {
        val state = Bundle()
        fragmentSavedStates.forEach { (key, fragmentState) ->
            state.putParcelable(key.toString(), fragmentState)
        }
        return state
    }

    fun restoreHelperState(savedState: Bundle) {
        fragmentSavedStates.clear()
        savedState.classLoader = this.javaClass.classLoader
        savedState.keySet().forEach { key ->
            fragmentSavedStates[key.toInt()] = savedState.getParcelable(key)
        }
    }
}
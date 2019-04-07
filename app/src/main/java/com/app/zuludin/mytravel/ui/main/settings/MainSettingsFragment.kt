package com.app.zuludin.mytravel.ui.main.settings

import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import com.app.zuludin.mytravel.R

class MainSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_settings, rootKey)
    }
}
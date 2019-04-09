package com.app.zuludin.mytravel.utils

import android.content.Context
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.app.zuludin.mytravel.data.source.local.TravelDbHelper
import java.text.NumberFormat
import java.util.*

fun TextView.currencyText(price: Int?) {
    val localeId = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeId)

    this.text = numberFormat.format(price)
}

fun AppCompatActivity.addFragment(fragment: androidx.fragment.app.Fragment, @IdRes frameId: Int) {
    supportFragmentManager.beginTransaction()
        .add(frameId, fragment)
        .commit()
}

fun AppCompatActivity.replaceFragment(fragment: androidx.fragment.app.Fragment, @IdRes frameId: Int) {
    supportFragmentManager.beginTransaction()
        .replace(frameId, fragment)
        .commit()
}

fun AppCompatActivity.toolbarTitle(title: String, subtitle: String? = null) {
    supportActionBar?.apply {
        this.title = title
        this.subtitle = subtitle
    }
}

val Context.travelDb: TravelDbHelper
    get() = TravelDbHelper.getInstance(applicationContext)
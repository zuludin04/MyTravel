package com.app.zuludin.mytravel.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

fun TextView.currencyText(price: Int?) {
    val localeId = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeId)

    this.text = numberFormat.format(price)
}

fun AppCompatActivity.addFragment(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.beginTransaction()
        .add(frameId, fragment)
        .commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes frameId: Int) {
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
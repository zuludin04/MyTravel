package com.app.zuludin.mytravel.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

object BindingUtils {

    @BindingAdapter("app:adultPrice", "app:totalAdult", requireAll = false)
    @JvmStatic
    fun setAdultPrice(textView: TextView, adultPrice: Int, totalAdult: Int) {
        val totalPrice = adultPrice.times(totalAdult)
        textView.currencyText(totalPrice)
    }

    @BindingAdapter("app:childPrice", "app:totalChild", requireAll = false)
    @JvmStatic
    fun setChildPrice(textView: TextView, childPrice: Int, totalChild: Int) {
        val price = childPrice.div(2)
        val totalPrice = price.times(totalChild)
        textView.currencyText(totalPrice)
    }

    @BindingAdapter("app:rentalPrice")
    @JvmStatic
    fun setRentalPrice(textView: TextView, price: Int) {
        textView.currencyText(price)
    }

    @BindingAdapter("app:rentalDailyPrice", "app:rentalDurations", requireAll = false)
    @JvmStatic
    fun setRentalTotalPrice(textView: TextView, price: Int, duration: String) {
        val total = price.times(duration.toInt())
        textView.currencyText(total)
    }

    @BindingAdapter("app:loadImageUrl")
    @JvmStatic
    fun setLoadImageUrl(imageView: ImageView, url: String) {
        Picasso
            .get()
            .load(url)
            .into(imageView)
    }
}
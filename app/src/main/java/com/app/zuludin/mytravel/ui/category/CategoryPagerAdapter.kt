package com.app.zuludin.mytravel.ui.category

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CategoryPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    private val pagerCategory = arrayListOf("Beach", "Theme Park", "Museum", "Zoo", "Restaurant")

    override fun getItem(position: Int): Fragment = CategoryListFragment.getInstance(pagerCategory[position])

    override fun getCount(): Int = pagerCategory.size

    override fun getPageTitle(position: Int): CharSequence? = pagerCategory[position]
}
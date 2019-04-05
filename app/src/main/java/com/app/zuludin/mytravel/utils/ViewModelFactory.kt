package com.app.zuludin.mytravel.utils

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.app.zuludin.mytravel.ui.category.CategoryViewModel
import com.app.zuludin.mytravel.ui.explore.ExploreDetailViewModel
import com.app.zuludin.mytravel.ui.main.home.MainExploreViewModel
import com.app.zuludin.mytravel.ui.tickets.list.TicketListViewModel

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainExploreViewModel::class.java) -> MainExploreViewModel(
                application,
                CoroutineContextProvider()
            ) as T
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(
                application,
                CoroutineContextProvider()
            ) as T
            modelClass.isAssignableFrom(ExploreDetailViewModel::class.java) -> ExploreDetailViewModel(
                application,
                CoroutineContextProvider()
            ) as T
            modelClass.isAssignableFrom(TicketListViewModel::class.java) -> TicketListViewModel(
                application,
                CoroutineContextProvider()
            ) as T
            else -> throw RuntimeException("Unknown ViewModel")
        }
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}
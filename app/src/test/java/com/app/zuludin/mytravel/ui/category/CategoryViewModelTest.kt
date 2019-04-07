package com.app.zuludin.mytravel.ui.category

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.TestContextProvider
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.eq
import com.google.common.collect.Lists
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CategoryViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: TravelDataRepository

    @Mock
    private lateinit var exploreList: MutableLiveData<List<TravelExplore>>

    private lateinit var viewModel: CategoryViewModel

    private lateinit var results: List<TravelExplore>

    @Before
    fun setupCategoryTest() {
        MockitoAnnotations.initMocks(this)

        viewModel = CategoryViewModel(application, TestContextProvider())

        results = Lists.newArrayList(
            TravelExplore(name = "Name One", location = "Location One"),
            TravelExplore(name = "Name Two", location = "Location Two")
        )
    }

    @Test
    fun `explore by category test`() {
        viewModel.exploreByCategoryDataList = exploreList

        runBlocking {
            Mockito.`when`(repository.loadExploreByCategory("beach")).thenReturn(results)
            Mockito.`when`(viewModel.exploreByCategoryDataList.value).thenReturn(results)
            viewModel.loadCategoryExplores("beach")
            assertEquals(2, viewModel.exploreByCategoryDataList.value?.size)
        }
    }
}
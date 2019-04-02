package com.app.zuludin.mytravel.ui.main.home

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.TestContextProvider
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.TravelDataSource
import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.google.common.collect.Lists
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.*

class MainExploreViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: TravelDataRepository

    @Mock
    private lateinit var exploreList: MutableLiveData<List<ExploreList>>

//    @Captor
//    private lateinit var exploreCaptureCallback: ArgumentCaptor<TravelDataSource.ExploreDataListCallback>

    private lateinit var viewModel: MainExploreViewModel

    private lateinit var explores: List<ExploreList>

    @Before
    fun setupExploreTest() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainExploreViewModel(application, TestContextProvider())

        val recom: List<TravelExplore> = ArrayList(5)
        explores = Lists.newArrayList(
            ExploreList(recom, "Recommendations"),
            ExploreList(recom, "Recent")
        )
    }

    @Test
    fun mainExploreTest() {
        viewModel.travelExploreDataList = exploreList

        runBlocking {
            Mockito.`when`(repository.loadMainExploreData()).thenReturn(explores)
            Mockito.`when`(viewModel.travelExploreDataList.value).thenReturn(explores)
            viewModel.loadExploreList()
            assertEquals(2, viewModel.travelExploreDataList.value?.size)
        }
    }
}
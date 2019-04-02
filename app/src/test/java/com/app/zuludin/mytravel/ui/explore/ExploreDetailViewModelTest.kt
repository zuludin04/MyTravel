package com.app.zuludin.mytravel.ui.explore

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.TestContextProvider
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ExploreDetailViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var exploreDetailData: MutableLiveData<TravelExplore>

    @Mock
    private lateinit var repository: TravelDataRepository

    private lateinit var viewModel: ExploreDetailViewModel

    private lateinit var explore: TravelExplore

    @Before
    fun setupDetailExploreTest() {
        MockitoAnnotations.initMocks(this)

        viewModel = ExploreDetailViewModel(application, TestContextProvider())

        explore = TravelExplore(dataId = 1, name = "One", category = "Cat")
    }

    @Test
    fun `detail explore data test`() {
        viewModel.exploreDetailData = exploreDetailData

        runBlocking {
            Mockito.`when`(repository.loadDetailExplore(1)).thenReturn(explore)
            Mockito.`when`(viewModel.exploreDetailData.value).thenReturn(explore)

            viewModel.loadDetailExplore(1)
            assertEquals("One", viewModel.exploreDetailData.value?.name)
        }
    }
}
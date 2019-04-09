package com.app.zuludin.mytravel.ui.main.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.zuludin.mytravel.capture
import com.app.zuludin.mytravel.data.TravelDataRepository
import com.app.zuludin.mytravel.data.TravelDataSource
import com.app.zuludin.mytravel.data.model.local.Favourite
import com.google.common.collect.Lists
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainFavouriteViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var favouritesData: MutableLiveData<List<Favourite>>

    @Mock
    private lateinit var repository: TravelDataRepository

    @Captor
    private lateinit var favouriteCallbackCaptor: ArgumentCaptor<TravelDataSource.GetFavouritePlacesCallback>

    private lateinit var viewModel: MainFavouriteViewModel

    private lateinit var favourites: List<Favourite>

    @Before
    fun `setup favourite viewModel test`() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainFavouriteViewModel(application)

        favourites = Lists.newArrayList(
            Favourite(dataId = "1", name = "Beach"),
            Favourite(dataId = "2", name = "Theme Park")
        )
    }

    @Test
    fun `favourite explore list test`() {
        with(viewModel) {
            favouriteExploreData = favouritesData
            loadFavouritePlaces()

            verify<TravelDataRepository>(repository).loadFavouritePlaces(capture(favouriteCallbackCaptor))
            favouriteCallbackCaptor.value.onFavourites(favourites)

            verify(favouriteExploreData).value = favourites
        }
    }
}
package com.app.zuludin.mytravel.data.model.local

import com.app.zuludin.mytravel.data.model.remote.Activities
import com.app.zuludin.mytravel.data.model.remote.Food
import com.app.zuludin.mytravel.data.model.remote.Hotel

data class ExploreActivities(val activities: List<Activities>)

data class ExploreFoods(val foods: List<Food>)

data class ExploreHotels(val hotels: List<Hotel>)
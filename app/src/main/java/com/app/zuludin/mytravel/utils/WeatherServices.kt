package com.app.zuludin.mytravel.utils

import com.app.zuludin.mytravel.data.model.remote.CurrentWeather
import com.app.zuludin.mytravel.data.model.remote.ForecastWeather
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {

    @GET("forecast.json")
    fun getForecastWeatherAsync(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: String): Deferred<Response<ForecastWeather>>

    @GET("current.json")
    fun getCurrentWeatherAsync(
        @Query("key") key: String,
        @Query("q") q: String): Deferred<Response<CurrentWeather>>
}
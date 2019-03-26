package com.app.zuludin.mylibrary.dialog.listener

import com.app.zuludin.mylibrary.dialog.model.Airport
import com.app.zuludin.mylibrary.dialog.model.City
import com.app.zuludin.mylibrary.dialog.model.Station

interface AirportListener {
    fun onAirportClick(airport: Airport)
}

interface CityListener {
    fun onCityClick(city: City)
}

interface StationListener {
    fun onStationClick(station: Station)
}

interface CalendarListener {
    fun onSelectDate(selectedDate: Long)
}

interface ContactInputListener {
    fun onContact(name: String)
}

interface PassengerInputListener {
    fun onPassenger(name: String, position: Int)
}
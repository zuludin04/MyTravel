package com.app.zuludin.mytravel.utils

import android.content.Context
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.CategoryItem
import com.app.zuludin.mytravel.data.model.local.CategoryList
import com.app.zuludin.mytravel.data.model.remote.*

object DataProvider {

    fun categoryList(): CategoryList {
        val list: MutableList<CategoryItem> = mutableListOf()

        val name = arrayListOf("Beach", "Theme Park", "Museum", "Zoo", "Restaurant")
        val total = arrayListOf("30", "63", "43", "78", "72", "59", "120")
        val icon = arrayListOf(R.drawable.beach, R.drawable.theme_park, R.drawable.museum, R.drawable.zoo, R.drawable.restaurant)

        for (i in name.indices) {
            list.add(CategoryItem(icon[i], name[i], total[i]))
        }

        return CategoryList(list, "Category")
    }

    fun hotelsData(
        city: String,
        location: String,
        checkIn: String,
        checkOut: String,
        stay: Int,
        totalGuest: String,
        totalRoom: String
    ): List<Hotel> {
        val list: MutableList<Hotel> = mutableListOf()

        list.add(
            Hotel(
                hotelImage = R.drawable.hotel_room,
                hotelName = "Hotel Indah Kapuk",
                hotelPrice = 860000,
                hotelRating = 3.4,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.bali,
                hotelName = "Hotel Jaya",
                hotelPrice = 1200000,
                hotelRating = 3.0,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.singapore,
                hotelName = "Hotel Nusa Dua",
                hotelPrice = 1500000,
                hotelRating = 4.2,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.hotel_room,
                hotelName = "Hotel Bangsa Bangsa",
                hotelPrice = 2300000,
                hotelRating = 3.8,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.bali,
                hotelName = "Hotel Harapan",
                hotelPrice = 3800000,
                hotelRating = 4.5,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.singapore,
                hotelName = "Hotel Purnama",
                hotelPrice = 4000000,
                hotelRating = 4.3,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.hotel_room,
                hotelName = "Hotel Surya Kencana",
                hotelPrice = 5200000,
                hotelRating = 4.5,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.bali,
                hotelName = "Hotel Jaja",
                hotelPrice = 5500000,
                hotelRating = 2.5,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )
        list.add(
            Hotel(
                hotelImage = R.drawable.singapore,
                hotelName = "Hotel Angkasa Pribadi",
                hotelPrice = 6000000,
                hotelRating = 4.9,
                timeIn = "14:00",
                timeOut = "13:00",
                hotelCity = city,
                hotelLocation = location,
                checkIn = checkIn,
                checkOut = checkOut,
                stayDuration = stay,
                totalGuest = totalGuest,
                totalRoom = totalRoom
            )
        )

        return list
    }

    fun hotelRooms(hotelName: String): List<HotelRoom> {
        val rooms: ArrayList<HotelRoom> = arrayListOf()
        rooms.add(HotelRoom(hotelName, "Deluxe", 860000))
        rooms.add(HotelRoom(hotelName, "Regular", 1200000))
        rooms.add(HotelRoom(hotelName, "Double Deluxe", 2000000))
        return rooms
    }

    fun flightTickets(
        airportOrigin: String,
        airportDestination: String,
        originCity: String,
        destinationCity: String,
        originCode: String,
        destinationCode: String,
        adult: Int,
        child: Int,
        infant: Int,
        seatClass: String,
        originDate: String,
        destinationDate: String
    ): List<Flight> {
        val list: MutableList<Flight> = mutableListOf()

        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Garuda Indonesia",
                airlineIcon = R.drawable.garuda_indonesia,
                flightPrice = 850000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Lion Air",
                airlineIcon = R.drawable.lion_air,
                flightPrice = 1150000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Batik Air",
                airlineIcon = R.drawable.batik_air,
                flightPrice = 1500000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Fly Emirates",
                airlineIcon = R.drawable.fly_emirates,
                flightPrice = 1800000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Malaysia Airlines",
                airlineIcon = R.drawable.malaysia_airlines,
                flightPrice = 2210000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Citilink",
                airlineIcon = R.drawable.citilink,
                flightPrice = 2750000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Air Asia",
                airlineIcon = R.drawable.air_asia,
                flightPrice = 3000000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )
        list.add(
            Flight(
                flightDuration = "3h 45m",
                airlineName = "Sriwijaya Air",
                airlineIcon = R.drawable.sriwijaya_air,
                flightPrice = 4250000,
                originTime = "13:40",
                destinationTime = "17:20",
                baggage = "20 kg",
                machine = "Boeing-727",
                seatLayout = "3x3 inch",
                airportOrigin = airportOrigin,
                airportDestination = airportDestination,
                originCity = originCity,
                destinationCity = destinationCity,
                originCode = originCode,
                destinationCode = destinationCode,
                adultPassenger = adult,
                childPassenger = child,
                infantPassenger = infant,
                seatClass = seatClass,
                originDate = originDate,
                destinationDate = destinationDate
            )
        )

        return list
    }

    fun trainTickets(
        stationOrigin: String,
        stationDestination: String,
        originCity: String,
        destinationCity: String,
        originCode: String,
        destinationCode: String,
        adult: Int,
        child: Int,
        seatClass: String,
        departureDate: String
    ): List<Train> {
        val list: MutableList<Train> = mutableListOf()

        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 110000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 150000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 200000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 240000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 280000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 310000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 380000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )
        list.add(
            Train(
                trainDuration = "3h 45m",
                argoName = "Argo Parahyangan",
                trainPrice = 450000,
                timeOrigin = "18:40",
                timeDestination = "21:50",
                stationOrigin = stationOrigin,
                stationDestination = stationDestination,
                cityOrigin = originCity,
                cityDestination = destinationCity,
                codeOrigin = originCode,
                codeDestination = destinationCode,
                adult = adult,
                child = child,
                seatClass = seatClass,
                departureDate = departureDate
            )
        )

        return list
    }

    fun rentalCarList(
        duration: String,
        pickup: String,
        start: String,
        finish: String,
        location: String
    ): List<CarRental> {
        val list: MutableList<CarRental> = mutableListOf()

        list.add(
            CarRental(
                carName = "Avanza",
                rentalName = "Surya Kencana Rental",
                rentalPrice = 250000,
                carImage = R.drawable.avanza,
                rentalRating = 3.3,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )
        list.add(
            CarRental(
                carName = "Xenia",
                rentalName = "Rental Abadi",
                rentalPrice = 295000,
                carImage = R.drawable.xenia,
                rentalRating = 3.7,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )
        list.add(
            CarRental(
                carName = "Agya",
                rentalName = "Rental Primus",
                rentalPrice = 330000,
                carImage = R.drawable.agya,
                rentalRating = 4.0,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )
        list.add(
            CarRental(
                carName = "Ertiga",
                rentalName = "Sabar Rental",
                rentalPrice = 365000,
                carImage = R.drawable.ertiga,
                rentalRating = 4.4,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )
        list.add(
            CarRental(
                carName = "Innova",
                rentalName = "Rental Sabar Makmur",
                rentalPrice = 380000,
                carImage = R.drawable.innova,
                rentalRating = 4.2,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )
        list.add(
            CarRental(
                carName = "Brio",
                rentalName = "Sukaneka Rental",
                rentalPrice = 450000,
                carImage = R.drawable.brio,
                rentalRating = 4.6,
                rentalDuration = duration,
                pickupTime = pickup,
                startDate = start,
                finishDate = finish,
                rentalLocation = location
            )
        )

        return list
    }

    fun dropdownList(context: Context, arrayList: Int): List<String> {
        val list: MutableList<String> = mutableListOf()
        val items = context.resources.getStringArray(arrayList)
        for (i in items) {
            list.add(i)
        }
        return list
    }

    fun getAtmTransferInstructions(isTransfer: Boolean, bank: String): List<String> {
        val transactions: MutableList<String> = mutableListOf()

        if (!isTransfer) {
            when (bank) {
                "BCA" -> {
                    transactions.addAll(
                        arrayListOf(
                            "On the main menu, Choose Other Transaction",
                            "Choose Transfer",
                            "Choose To BCA Virtual Account",
                            "Enter your Payment Code (11 digit code) and press Correct",
                            "Enter full amount to be paid and press Correct",
                            "Your payment details will appear on the confirmation page. If information is correct press Yes"
                        )
                    )
                }
                "Mandiri" -> {
                    transactions.addAll(
                        arrayListOf(
                            "On the main menu, Choose Pay/Buy",
                            "Choose Others",
                            "Choose Multi Payment",
                            "Enter Company Code and press Correct",
                            "Enter your Payment Code and press Correct",
                            "Your payment details will appear on the confirmation page. If information is correct press Yes"
                        )
                    )
                }
                "BRI" -> {
                    transactions.addAll(
                        arrayListOf(
                            "On the main menu, Choose Other Transaction",
                            "Choose Payment",
                            "Choose Others",
                            "Choose BRIVA",
                            "Enter your BRIVA number correctly and press Yes",
                            "Your payment details will appear on the confirmation page. If information is correct press Yes"
                        )
                    )
                }
                "BNI" -> {
                    transactions.addAll(
                        arrayListOf(
                            "On the main menu, Choose Others",
                            "Choose Transfers",
                            "Choose Savings Account",
                            "Choose to BNI Account",
                            "Enter the Payment Account Number and press Yes",
                            "Enter the full amount to be paid and press Correct",
                            "Your payment details will appear on the confirmation page. If information is correct press Yes"
                        )
                    )
                }
            }
        } else {
            transactions.addAll(
                arrayListOf(
                    "One",
                    "Two",
                    "Three"
                )
            )
        }

        return transactions
    }

    fun getMobileBankingTransactions(bank: String): List<String> {
        val transactions: MutableList<String> = mutableListOf()

        when (bank) {
            "klikBCA" -> {
                transactions.addAll(
                    arrayListOf(
                        "Open KlikBCA and login",
                        "Click Transfer",
                        "Click Transfer to Virtual Account",
                        "Enter Virtual Account number and then click Continue",
                        "Enter Response Key BCA and click Send"
                    )
                )
            }
            "Mandiri Online" -> {
                transactions.addAll(
                    arrayListOf(
                        "Open Mandiri Online app and login",
                        "On main menu, click Pay",
                        "Click Multi Payment",
                        "Choose Service Provider",
                        "Enter payment code and then click Continue",
                        "Recheck your transaction and finish the payment"
                    )
                )
            }
            "Mobile Banking BRI" -> {
                transactions.addAll(
                    arrayListOf(
                        "Open Mobile Banking BRI from your phone",
                        "Click Info",
                        "Click BRIVA",
                        "Enter your BRIVA number",
                        "Enter full amount to be paid",
                        "Enter your PIN and then click Continue and you are done"
                    )
                )
            }
            "BNI Mobile Banking" -> {
                transactions.addAll(
                    arrayListOf(
                        "Open BNI Mobile Banking app and login",
                        "Click Transfer",
                        "Click To BNI Account",
                        "Click Input New Account",
                        "Enter account numbers, and then click Continue",
                        "Input the Transaction Password and then click Continue and you are done"
                    )
                )
            }
        }

        return transactions
    }

    fun getMerchantPaymentTransactions(): List<String> {
        val instructions: MutableList<String> = mutableListOf()

        instructions.addAll(
            arrayListOf(
                "After confirming your payment, we will issue you a unique Payment Code number",
                "Note down your Payment Code and total amount. Dont worry, we will also mail you a copy of the payment instructions",
                "Go to an Merchant store near you and provide the cashier with the Payment Code number or barcode to be scanned",
                "The cashier will then confirm the transaction by asking for the transaction amount and the merchant name",
                "Confirm the payment with the cashier",
                "Your transaction is successful"
            )
        )

        return instructions
    }
}
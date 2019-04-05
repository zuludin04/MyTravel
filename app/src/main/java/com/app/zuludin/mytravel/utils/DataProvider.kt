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
        checkIn: String,
        checkOut: String,
        stay: Int,
        totalGuest: String,
        totalRoom: String
    ): List<Hotel> {
        val list: MutableList<Hotel> = mutableListOf()

        list.add(
            Hotel(
                thumbnail = R.drawable.hotel_room,
                name = "Hotel Indah Kapuk",
                startFrom = 860000,
                rating = 3.4,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.bali,
                name = "Hotel Jaya",
                startFrom = 1200000,
                rating = 3.0,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.singapore,
                name = "Hotel Nusa Dua",
                startFrom = 1500000,
                rating = 4.2,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.hotel_room,
                name = "Hotel Bangsa Bangsa",
                startFrom = 2300000,
                rating = 3.8,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.bali,
                name = "Hotel Harapan",
                startFrom = 3800000,
                rating = 4.5,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.singapore,
                name = "Hotel Purnama",
                startFrom = 4000000,
                rating = 4.3,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.hotel_room,
                name = "Hotel Surya Kencana",
                startFrom = 5200000,
                rating = 4.5,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.bali,
                name = "Hotel Jaja",
                startFrom = 5500000,
                rating = 2.5,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
            )
        )
        list.add(
            Hotel(
                thumbnail = R.drawable.singapore,
                name = "Hotel Angkasa Pribadi",
                startFrom = 6000000,
                rating = 4.9,
                timeIn = "14:00",
                timeOut = "13:00",
                city = city,
                checkIn = checkIn,
                checkOut = checkOut,
                duration = stay,
                guest = totalGuest,
                room = totalRoom
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
package com.app.zuludin.mytravel.ui.tickets.detail.hotel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.ui.tickets.detail.hotel.room.RoomHotelFragment
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_hotel_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailHotelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_hotel_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotel: Hotel = arguments?.getParcelable(HOTEL_DATA)!!

        view.detail_hotel_image.setImageResource(hotel.hotelImage!!)
        view.detail_hotel_name.text = hotel.hotelName
        view.detail_hotel_rating.rating = hotel.hotelRating?.toFloat()!!
        view.detail_hotel_city.text = hotel.hotelCity

        view.room_price_started.currencyText(hotel.hotelPrice)

        view.select_hotel_room_button.setOnClickListener {
            fragmentManager?.beginTransaction()?.run {
                setCustomAnimations(
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_top,
                    R.anim.enter_from_top,
                    R.anim.exit_to_bottom
                )
                replace(R.id.frame_container, RoomHotelFragment.getInstance(hotel))
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        const val HOTEL_DATA = "hotelData"

        fun getInstance(hotel: Hotel): DetailHotelFragment {
            val fragment = DetailHotelFragment()
            val args = Bundle()

            args.putParcelable(HOTEL_DATA, hotel)
            fragment.arguments = args

            return fragment
        }
    }
}

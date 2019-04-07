package com.app.zuludin.mytravel.ui.tickets.detail.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Facility
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.ui.common.ReviewAdapter
import com.app.zuludin.mytravel.ui.tickets.detail.hotel.room.RoomHotelFragment
import com.app.zuludin.mytravel.utils.currencyText
import com.squareup.picasso.Picasso
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.detail_hotel_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailHotelFragment : Fragment() {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(Facility::class, R.layout.item_hotel_facility) { HotelFacilityViewHolder(it) }
        }
    }

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
        adapter.set(ArrayList())

        Picasso.get().load(hotel.thumbnail).into(view.detail_hotel_image)
        view.detail_hotel_name.text = hotel.name
        view.detail_hotel_rating.rating = hotel.rating?.toFloat()!!
        view.detail_hotel_city.text = hotel.city
        view.detail_guest_information.text = "${hotel.duration} Night - ${hotel.guest}"
        hotel.review?.let { view.review_pager.adapter = ReviewAdapter(it) }

        view.room_price_started.currencyText(hotel.startFrom)

        view.recycler_facilities.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = this@DetailHotelFragment.adapter
        }

        hotel.facilities?.let { adapter.insert(it) }

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

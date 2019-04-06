package com.app.zuludin.mytravel.ui.tickets.detail.hotel.room

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Hotel
import com.app.zuludin.mytravel.data.model.remote.HotelRoom
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.room_hotel_fragment.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class RoomHotelFragment : Fragment(), RoomClickListener {

    private lateinit var transaction: Transaction
    private lateinit var hotel: Hotel

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(HotelRoom::class, R.layout.item_hotel_room) {
                RoomHotelViewHolder(
                    it,
                    this@RoomHotelFragment
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.room_hotel_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction = Transaction()
        hotel = arguments?.getParcelable(ROOM_DATA)!!

        adapter.set(ArrayList())

        view.recycler_room_hotel.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(SpacingItemDecoration(16))
            adapter = this@RoomHotelFragment.adapter
        }

        hotel.rooms?.let { adapter.insert(it) }
    }

    override fun onRoomClick(hotelRoom: HotelRoom) {
        transaction.city = hotel.city
        transaction.book = hotel.name
        transaction.date = "Data : ${hotel.checkIn} - ${hotel.checkOut}"
        transaction.duration = "Duration : ${hotel.duration} days"
        transaction.service = "Room : ${hotelRoom.name}"
        transaction.type = "Hotel"
        transaction.adult = 0
        transaction.child = 0
        transaction.infant = 0

        val localeId = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeId)
        val totalPrice = hotel.duration?.let { hotelRoom.price?.times(it) }

        transaction.price = numberFormat.format(totalPrice)

        val intent = Intent(requireContext(), ReviewTicketActivity::class.java)
        intent.putExtra("Transaction", transaction)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    companion object {
        const val ROOM_DATA = "roomData"

        fun getInstance(room: Hotel): RoomHotelFragment {
            val fragment = RoomHotelFragment()
            val args = Bundle()

            args.putParcelable(ROOM_DATA, room)
            fragment.arguments = args

            return fragment
        }
    }
}

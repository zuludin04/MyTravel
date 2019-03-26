package com.app.zuludin.mytravel.ui.tickets.detail.rental

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.CarRental
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.app.zuludin.mytravel.utils.currencyText
import kotlinx.android.synthetic.main.detail_rental_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailRentalFragment : Fragment() {

    private lateinit var transaction: Transaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_rental_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction = Transaction()
        setupDetailLayout(view)

        view.book_button.setOnClickListener {
            val intent = Intent(requireContext(), ReviewTicketActivity::class.java)
            intent.putExtra("Transaction", transaction)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        }
    }

    private fun setupDetailLayout(view: View) {
        val rental: CarRental = arguments?.getParcelable(RENTAL_DATA)!!

        view.rental_detail_image.setImageResource(rental.carImage!!)
        view.rental_detail_car.text = rental.carName
        view.rental_detail_rental.text = rental.rentalName
        view.rental_detail_rating.rating = rental.rentalRating?.toFloat()!!

        view.detail_rental_city.text = rental.rentalLocation
        view.detail_rental_date.text = rental.startDate
        view.detail_rental_duration.text = "${rental.rentalDuration} days"
        view.detail_rental_machine.text = "RX Machine"
        view.detail_rental_seat.text = "6 seater"

        view.rental_price_title.text = "${rental.carName} x ${rental.rentalDuration} days"
        view.rental_price.currencyText(rental.rentalPrice)

        val duration = rental.rentalDuration?.toInt()

        val totalPrice = duration?.let { rental.rentalPrice?.times(it) }
        view.total_price.currencyText(totalPrice)

        view.recycler_review.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = CarReviewAdapter()
        }

        PagerSnapHelper().attachToRecyclerView(view.recycler_review)

        transaction.city = rental.rentalLocation
        transaction.book = rental.carName
        transaction.price = view.total_price.text.toString()
        transaction.service = "Rental : ${rental.rentalName}"
        transaction.date = "Date : ${rental.startDate}"
        transaction.duration = "Duration : ${view.detail_rental_duration.text}"
        transaction.type = "Rental"
        transaction.adult = 1
        transaction.child = 0
        transaction.infant = 0
    }

    companion object {
        const val RENTAL_DATA = "rentalData"

        fun getInstance(rental: CarRental): DetailRentalFragment {
            val fragment = DetailRentalFragment()
            val args = Bundle()

            args.putParcelable(RENTAL_DATA, rental)
            fragment.arguments = args

            return fragment
        }
    }
}

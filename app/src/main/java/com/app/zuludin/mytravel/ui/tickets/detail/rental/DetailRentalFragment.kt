package com.app.zuludin.mytravel.ui.tickets.detail.rental

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Rental
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.databinding.DetailRentalFragmentBinding
import com.app.zuludin.mytravel.ui.common.ReviewAdapter
import com.app.zuludin.mytravel.ui.tickets.review.ReviewTicketActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_rental_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailRentalFragment : Fragment() {

    private lateinit var transaction: Transaction
    private lateinit var binding: DetailRentalFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_rental_fragment, container, false)
        return binding.root
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
        val rental: Rental = arguments?.getParcelable(RENTAL_DATA)!!

        binding.rental = rental
        Picasso.get().load(rental.image).into(binding.rentalDetailImage)
        binding.rentalDetailRating.rating = rental.rating?.toFloat()!!
        rental.review?.let { view.review_pager.adapter = ReviewAdapter(it) }

        transaction.city = rental.region
        transaction.book = rental.car
        transaction.price = view.total_price.text.toString()
        transaction.service = "Rental : ${rental.rental}"
        transaction.date = "Date : ${rental.startDate}"
        transaction.duration = "Duration : ${view.detail_rental_duration.text}"
        transaction.type = "Rental"
        transaction.adult = 1
        transaction.child = 0
        transaction.infant = 0
    }

    companion object {
        const val RENTAL_DATA = "rentalData"

        fun getInstance(rental: Rental): DetailRentalFragment {
            val fragment = DetailRentalFragment()
            val args = Bundle()

            args.putParcelable(RENTAL_DATA, rental)
            fragment.arguments = args

            return fragment
        }
    }
}

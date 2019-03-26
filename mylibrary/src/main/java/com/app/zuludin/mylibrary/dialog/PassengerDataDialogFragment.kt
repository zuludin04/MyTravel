package com.app.zuludin.mylibrary.dialog

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.app.zuludin.mylibrary.R
import com.app.zuludin.mylibrary.dialog.listener.PassengerInputListener
import kotlinx.android.synthetic.main.passenger_dialog_fragment.view.*

class PassengerDataDialogFragment : BaseDialogFragment() {

    private lateinit var listener: PassengerInputListener

    override fun layoutId(): Int = R.layout.passenger_dialog_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.passenger_title.adapter = spinnerAdapter(R.array.title)
        view.passenger_id.adapter = spinnerAdapter(R.array.id_type)

        val type = arguments?.getString("Type")
        val position = arguments?.getInt("Position")!!

        if (type != "Adult") {
            view.passenger_id.visibility = View.GONE
            view.number.visibility = View.GONE
        }

        view.input_button.setOnClickListener {
            listener.onPassenger(
                "${view.passenger_title.selectedItem}. ${view.passenger_name.text.toString()} ($type)",
                position
            )
            dismiss()
        }
    }

    private fun spinnerAdapter(arrayId: Int): ArrayAdapter<String> {
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, spinnerList(arrayId))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun spinnerList(arrayId: Int): Array<String> = resources.getStringArray(arrayId)

    private fun setPassengerListener(listener: PassengerInputListener) {
        this.listener = listener
    }

    companion object {
        fun getInstance(position: Int, type: String, listener: PassengerInputListener): PassengerDataDialogFragment {
            val fragment = PassengerDataDialogFragment()
            val args = Bundle()

            args.putInt("Position", position)
            args.putString("Type", type)
            fragment.setPassengerListener(listener)
            fragment.arguments = args

            return fragment
        }
    }
}
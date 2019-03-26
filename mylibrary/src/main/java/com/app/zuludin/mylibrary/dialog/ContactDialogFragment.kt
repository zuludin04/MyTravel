package com.app.zuludin.mylibrary.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.app.zuludin.mylibrary.R
import com.app.zuludin.mylibrary.dialog.listener.ContactInputListener
import kotlinx.android.synthetic.main.contact_dialog_fragment.view.*

class ContactDialogFragment : BaseDialogFragment() {

    private lateinit var listener: ContactInputListener

    override fun layoutId(): Int = R.layout.contact_dialog_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.input_button.setOnClickListener {
            if (TextUtils.isEmpty(view.input_name.text) || TextUtils.isEmpty(view.input_email.text) || TextUtils.isEmpty(
                    view.input_phone.text
                )
            ) {
                Toast.makeText(requireContext(), "Please input data properly", Toast.LENGTH_SHORT).show()
            } else {
                listener.onContact(view.input_name.text.toString())
                dismiss()
            }
        }
    }

    fun setContactListener(listener: ContactInputListener) {
        this.listener = listener
    }

    companion object {
        fun getInstance(listener: ContactInputListener): ContactDialogFragment {
            val fragment = ContactDialogFragment()
            fragment.setContactListener(listener)
            return fragment
        }
    }
}
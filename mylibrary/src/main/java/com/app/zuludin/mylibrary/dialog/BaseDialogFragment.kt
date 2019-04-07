package com.app.zuludin.mylibrary.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mylibrary.R

abstract class BaseDialogFragment : androidx.fragment.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return dialog
    }

    @SuppressLint("CommitTransaction")
    override fun show(manager: androidx.fragment.app.FragmentManager, tag: String) {
        show(manager.beginTransaction(), tag)
    }

    override fun show(transaction: androidx.fragment.app.FragmentTransaction, tag: String): Int {
        return transaction
            .setCustomAnimations(R.anim.enter_from_bottom, 0, 0, R.anim.exit_to_bottom)
            .add(android.R.id.content, this, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun dismiss() {
        requireFragmentManager().popBackStack()
        super.dismiss()
    }

    abstract fun layoutId(): Int
}
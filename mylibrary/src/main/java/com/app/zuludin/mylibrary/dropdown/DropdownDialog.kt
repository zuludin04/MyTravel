package com.app.zuludin.mylibrary.dropdown

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.Window
import com.app.zuludin.mylibrary.R

class DropdownDialog(context: Context) : Dialog(context, R.style.FloatDropdownDialogTheme) {

    lateinit var rv: androidx.recyclerview.widget.RecyclerView

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.dropdown_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv = findViewById(R.id.dropdown_list_recycler)
        rv.setHasFixedSize(true)
    }
}

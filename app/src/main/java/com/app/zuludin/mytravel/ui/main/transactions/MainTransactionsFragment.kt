package com.app.zuludin.mytravel.ui.main.transactions

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.transaction.TransactionDetailActivity
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.app.zuludin.mytravel.utils.begone
import com.app.zuludin.mytravel.utils.visible
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.main_transactions_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainTransactionsFragment : Fragment() {

    private lateinit var adapter: TransactionAdapter
    private lateinit var itemView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_transactions_fragment, container, false)
        itemView = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionAdapter {
            val intent = Intent(requireContext(), TransactionDetailActivity::class.java)
            intent.putExtra("DATA_KEY", it.id)
            startActivityForResult(intent, UPDATE_DELETE_TRANSACTION)
        }
        setupTransactionList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_DELETE_TRANSACTION) {
            if (resultCode == RESULT_OK) {
                loadFirebaseTransaction()
            }
        }
    }

    private fun setupTransactionList() {
        itemView.recycler_transactions.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            addItemDecoration(SpacingItemDecoration(16))
            adapter = this@MainTransactionsFragment.adapter
        }

        loadFirebaseTransaction()
    }

    private fun loadFirebaseTransaction() {
        val list: MutableList<Transaction> = mutableListOf()
        val database = FirebaseDatabase.getInstance().reference
        val query: Query = database.child("Transaction")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount.toInt() > 0) {
                    for (data in snapshot.children) {
                        val transaction = data.getValue(Transaction::class.java)
                        transaction?.id = data.key.toString()
                        if (transaction != null) {
                            list.add(transaction)
                        }

                        adapter.refreshAdapterList(list)
                        itemView.recycler_transactions.visible()
                        itemView.empty_layout.begone()
                        itemView.progress_bar.begone()
                    }
                } else {
                    itemView.progress_bar.begone()
                    itemView.recycler_transactions.begone()
                    itemView.empty_layout.visible()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    companion object {
        private const val UPDATE_DELETE_TRANSACTION = 4
    }
}

package com.app.zuludin.mytravel.ui.main.transactions

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Transaction
import com.app.zuludin.mytravel.ui.transaction.TransactionDetailActivity
import com.app.zuludin.mytravel.utils.SpacingItemDecoration
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.main_transactions_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainTransactionsFragment : Fragment() {

    private val viewModel: MainTransactionViewModel by lazy {
        ViewModelProviders.of(this).get(MainTransactionViewModel::class.java)
    }

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

    override fun onStart() {
        super.onStart()
//        viewModel.getTransactions()
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
            layoutManager = LinearLayoutManager(requireContext())
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
                Log.d("size", "${snapshot.childrenCount} ${snapshot.children.count()}")
                if (snapshot.childrenCount.toInt() > 0) {
                    for (data in snapshot.children) {
                        val transaction = data.getValue(Transaction::class.java)
                        transaction?.id = data.key.toString()
                        if (transaction != null) {
                            list.add(transaction)
                        }

                        adapter.refreshAdapterList(list)
                        itemView.progress_bar.visibility = View.GONE
                    }
                } else {
                    itemView.progress_bar.visibility = View.GONE
                    itemView.recycler_transactions.visibility = View.GONE
                    itemView.empty_list.visibility = View.VISIBLE
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

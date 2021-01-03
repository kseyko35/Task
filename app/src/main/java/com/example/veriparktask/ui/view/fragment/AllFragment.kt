package com.example.veriparktask.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veriparktask.R
import com.example.veriparktask.databinding.FragmentAllBinding
import com.example.veriparktask.ui.adapter.StockAdapter
import com.example.veriparktask.util.Constants
import com.example.veriparktask.ui.viewmodel.AllViewModel
import kotlinx.android.synthetic.main.fragment_all.*


class AllFragment : BaseFragment() {

    private var adapter: StockAdapter? = null
    private lateinit var allViewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allViewModel = ViewModelProvider(this).get(AllViewModel::class.java)

        stock_recycle_view.layoutManager = LinearLayoutManager(stock_recycle_view.context)
        stock_recycle_view.setHasFixedSize(true)

        allViewModel.stockList?.observe(viewLifecycleOwner, Observer {

            for (stock in it.stocks) {
                stock.encryptData = decryption(
                    stock.symbol, context?.getSharedPreferences(
                        Constants.PREFS_FILENAME,
                        Context.MODE_PRIVATE
                    )
                ).toString()

            }
            adapter = StockAdapter(it.stocks, String(Constants.PERIOD_ALL))
            stock_recycle_view.adapter = adapter
        })
        stock_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })
    }

}
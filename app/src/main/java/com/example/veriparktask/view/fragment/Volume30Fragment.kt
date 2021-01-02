package com.example.veriparktask.view.fragment

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
import com.example.veriparktask.adapter.StockAdapter
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.viewmodel.Volume30ViewModel
import kotlinx.android.synthetic.main.volume30_fragment.*

class Volume30Fragment : BaseFragment() {

    private var adapter: StockAdapter? = null
    private lateinit var viewModel: Volume30ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.volume30_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(Volume30ViewModel::class.java)

        stock_recycle_view.layoutManager = LinearLayoutManager(stock_recycle_view.context)
        stock_recycle_view.setHasFixedSize(true)
        viewModel.stockList?.observe(viewLifecycleOwner, Observer {


            for (stock in it.stocks) {
                stock.encryptData = decryption(
                    stock.symbol, context?.getSharedPreferences(
                        Constants.PREFS_FILENAME,
                        Context.MODE_PRIVATE
                    )
                ).toString()

            }
            adapter = StockAdapter(it.stocks, String(Constants.PERIOD_VOLUME30))
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
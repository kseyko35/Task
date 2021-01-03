package com.example.veriparktask.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.veriparktask.R
import com.example.veriparktask.util.Constants
import com.example.veriparktask.data.model.response.Stocks
import java.util.*
import kotlin.collections.ArrayList
import com.example.veriparktask.databinding.RecyclerviewRowBinding


class StockAdapter(
    private var stockList: List<Stocks>,
    period: String
) : RecyclerView.Adapter<StockAdapter.StockHolder>(),
    Filterable {


    private var stocksListEmpty: List<Stocks>
    private var mPeriod: String


    init {
        stocksListEmpty = stockList
        mPeriod = period
    }
    class StockHolder(val binding: RecyclerviewRowBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val binding=RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StockHolder(binding)
    }

    override fun getItemCount(): Int {
        return stocksListEmpty.size
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        with(holder){
            with(stocksListEmpty[position]){
                binding.symbol.text = encryptData
                binding.price.text = price.toString()
                binding.difference.text = difference.toString()
                binding.volume.text = volume.toString()
                binding.bid.text = bid.toString()
                binding.offer.text = offer.toString()
                if (stocksListEmpty[position].isUp) binding.isUp.setImageResource(R.mipmap.ic_up)
                else binding.isUp.setImageResource(R.mipmap.ic_down)
                if (position.rem(2) == 0) holder.itemView.setBackgroundResource(R.color.rowBckColor)
            }
        }

        holder.itemView.setOnClickListener {
            when (mPeriod) {
                String(Constants.PERIOD_ALL) -> it.findNavController().navigate(
                    R.id.action_all_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )
                String(Constants.PERIOD_INCREASING) -> it.findNavController().navigate(
                    R.id.action_increasing_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )

                String(Constants.PERIOD_DECREASING) -> it.findNavController().navigate(
                    R.id.action_decreasing_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )
                String(Constants.PERIOD_VOLUME30) -> it.findNavController().navigate(
                    R.id.action_volume30Fragment_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )
                String(Constants.PERIOD_VOLUME50) -> it.findNavController().navigate(
                    R.id.action_volume50Fragment_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )

                String(Constants.PERIOD_VOLUME100) -> it.findNavController().navigate(
                    R.id.action_volume100Fragment_to_detailFragment,
                    bundleOf("id" to stocksListEmpty[position].id)
                )


            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {

                    stocksListEmpty = stockList
                } else {
                    stocksListEmpty = stockList
                    val resultList = ArrayList<Stocks>()
                    for (row in stocksListEmpty) {
                        if (row.encryptData.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    stocksListEmpty = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = stocksListEmpty
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                stocksListEmpty = results?.values as ArrayList<Stocks>
                notifyDataSetChanged()
            }

        }

    }



}



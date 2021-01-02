package com.example.veriparktask.adapter




import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.veriparktask.R
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.network.model.response.Stocks
import kotlinx.android.synthetic.main.recyclerview_row.view.*
import java.util.*
import kotlin.collections.ArrayList


class StockAdapter(
    private var stockList: List<Stocks>,
    period: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {


    private var stocksListEmpty: List<Stocks>
    private lateinit var mcontext: Context
    private var mPeriod: String


    class StockHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        stocksListEmpty = stockList
        mPeriod = period

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val stockListView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        val stockHolder = StockHolder(stockListView)
        mcontext = parent.context

        return stockHolder
    }

    override fun getItemCount(): Int {
        return stocksListEmpty.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.select_stock_container.setBackgroundColor(Color.TRANSPARENT)

        holder.itemView.symbol.text = stocksListEmpty[position].encryptData
        holder.itemView.price.text = stocksListEmpty[position].price.toString()
        holder.itemView.difference.text = stocksListEmpty[position].difference.toString()
        holder.itemView.volume.text = stocksListEmpty[position].volume.toString()
        holder.itemView.bid.text = stocksListEmpty[position].bid.toString()
        holder.itemView.offer.text = stocksListEmpty[position].offer.toString()
        if (position.rem(2) == 0) holder.itemView.setBackgroundResource(R.color.rowBckColor)
        if (stocksListEmpty[position].isUp) holder.itemView.isUp.setImageResource(R.mipmap.ic_up)
        else holder.itemView.isUp.setImageResource(R.mipmap.ic_down)


        holder.itemView.setOnClickListener {
            Log.v("Tag:", holder.position.toString() + " holder position")
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                stocksListEmpty = results?.values as ArrayList<Stocks>
                notifyDataSetChanged()
            }

        }
    }


}



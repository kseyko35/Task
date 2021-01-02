package com.example.veriparktask.view.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.veriparktask.R
import com.example.veriparktask.constant.Constants
import com.example.veriparktask.viewmodel.DetailViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : BaseFragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            getView()?.windowToken,
            0
        ) // Detay acilirken keyboard acik kaldigi icin gizlemek icin kullanildi

        var factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailViewModel(
                    activity!!.application,
                    arguments?.getInt("id")
                ) as T
            }
        }
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        viewModel.stockDetail.observe(viewLifecycleOwner, Observer {
            val symbol = decryption(
                it.symbol, context?.getSharedPreferences(
                    Constants.PREFS_FILENAME,
                    Context.MODE_PRIVATE
                )
            )
            sembol.text = "Sembol : $symbol"

            fiyat.text = "Fiyat : ${it.price}"
            fark.text = "% Fark : ${it.difference}"
            hacim.text = "Hacim : ${it.volume}"
            alis.text = "Alış : ${it.bid}"
            satis.text = "Satış : ${it.offer}"
            gunluk_dusuk.text = "Günlük Düşük : ${it.lowest}"
            gunluk_yuksek.text = "Günlük Yüksek : ${it.highest}"
            adet.text = "Adet : ${it.count}"
            tavan.text = "Tavan : ${it.maximum}"
            taban.text = "Taban : ${it.minimum}"
            val graphicData = it.graphicData.map { it.value }
            if (it.isUp) isUp.setImageResource(R.mipmap.ic_up)
            else isUp.setImageResource(R.mipmap.ic_down)
            val aaChartModel: AAChartModel = AAChartModel()
                .chartType(AAChartType.Line)
                .animationType(AAChartAnimationType.EaseInCirc)
                .yAxisLabelsEnabled(false)
                .backgroundColor("#f4f4f4")
                .gradientColorEnable(true)
                .dataLabelsEnabled(false)
                .colorsTheme(arrayOf("#b94430"))
                .series(
                    arrayOf(
                        AASeriesElement()
                            .name(symbol)
                            .data(graphicData.toTypedArray())

                    )
                )
            aa_chart_view.aa_drawChartWithChartModel(aaChartModel)

        })


    }


}
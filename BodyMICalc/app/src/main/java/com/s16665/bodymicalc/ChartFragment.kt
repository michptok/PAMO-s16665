package com.s16665.bodymicalc

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class ChartFragment : Fragment() {
    var pieChart: PieChart? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.chart_fragment, container, false)
        pieChart = v.findViewById(R.id.piechart)
        setupPieChart()
        loadPieChartData()
        return v
    }

    private fun setupPieChart() {
        pieChart?.setDrawHoleEnabled(true)
        pieChart?.setUsePercentValues(false)
        pieChart?.setEntryLabelTextSize(16f)
        pieChart?.setEntryLabelColor(Color.BLACK)
        pieChart?.setCenterText("Ludzie z różnych krajów vs COVID")
        pieChart?.setCenterTextSize(20f)
        pieChart?.getDescription()?.isEnabled = false
        val legend = pieChart?.getLegend()
        legend?.orientation = Legend.LegendOrientation.VERTICAL
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend?.setDrawInside(false)
        legend?.isEnabled = true
    }

    private fun loadPieChartData() {
        val entries: MutableList<PieEntry?> = ArrayList()
        entries.add(PieEntry(2000f, "Zaszczepieni"))
        entries.add(PieEntry(5000f, "Zarażonych"))
        entries.add(PieEntry(3000f, "Zmarło"))
        entries.add(PieEntry(1000f, "Ozdrowieni"))
        val colors = ArrayList<Int?>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }
        val dataSet = PieDataSet(entries, "Ludzie z różnych krajów vs COVID")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(16f)
        data.setValueTextColor(Color.WHITE)
        pieChart?.setData(data)
        pieChart?.invalidate()
    }
}
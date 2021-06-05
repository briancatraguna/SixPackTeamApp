package com.bangkit.responderapp.ui

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.databinding.ItemReportBinding
import java.util.*
import kotlin.collections.ArrayList

class ListReportsAdapter(private val context: Context): RecyclerView.Adapter<ListReportsAdapter.ListViewHolder>() {

    private var listData = ArrayList<ReportEntity?>()
    fun setData(list: ArrayList<ReportEntity?>){
        this.listData.clear()
        this.listData = list
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemReportBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity?){
            with(binding){
                tvReportTitle.text = report?.classification
                tvLocation.text = getLocation(report?.latitude,report?.longitude)
                tvDate.text = report?.date
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,DetailReportActivity::class.java)
                intent.putExtra(DetailReportActivity.EXTRA_REPORT,report)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    private fun getLocation(latitude: Double?, longitude: Double?): String{
        var cityName = ""
        var geoCoder = Geocoder(context, Locale.getDefault())
        var address = geoCoder.getFromLocation(latitude!!,longitude!!,1)
        cityName = address.get(0).locality

        return cityName
    }

}
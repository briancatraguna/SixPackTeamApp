package com.bangkit.responderapp.ui

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.responderapp.R
import com.bangkit.responderapp.data.ReportEntity
import com.bangkit.responderapp.data.ReportIdEntity
import com.bangkit.responderapp.databinding.ItemReportBinding
import com.bangkit.responderapp.utils.LocationHelper
import java.util.*
import kotlin.collections.ArrayList

class ListReportsAdapter(private val context: Context): RecyclerView.Adapter<ListReportsAdapter.ListViewHolder>() {

    companion object{
        private const val crime = "Crime"
        private const val medical = "Medical"
        private const val fire = "Fire"
        private const val naturalDisaster = "Natural Disaster"
        private const val trafficAccident = "Traffic Accident"
        private const val unknown = "Unknown"
    }

    private val locationHelper = LocationHelper(context)

    private var listData = ArrayList<ReportEntity?>()
    fun setData(list: ArrayList<ReportEntity?>){
        this.listData.clear()
        this.listData = list
        notifyDataSetChanged()
    }

    private var listReportIds: ReportIdEntity? = ReportIdEntity(arrayListOf())
    fun setReportIds(reportIds: ReportIdEntity?){
        this.listReportIds = reportIds
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemReportBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity?,id: String?){
            with(binding){
                tvReportTitle.text = report?.classification
                tvLocation.text = locationHelper.getLocation(report?.latitude,report?.longitude)
                tvDate.text = report?.date
                if (report?.classification == crime){
                    imgLogo.setImageResource(R.drawable.ic_crime_logo)
                } else if (report?.classification == medical){
                    imgLogo.setImageResource(R.drawable.ic_medical_logo)
                } else if (report?.classification == fire){
                    imgLogo.setImageResource(R.drawable.ic_fire_logo)
                } else if (report?.classification == naturalDisaster){
                    imgLogo.setImageResource(R.drawable.ic_natural_disaster_logo)
                } else if (report?.classification == trafficAccident){
                    imgLogo.setImageResource(R.drawable.ic_traffic_accident_logo)
                } else {
                    imgLogo.setImageResource(R.drawable.ic_unknown_logo)
                }
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,DetailReportActivity::class.java)
                intent.putExtra(DetailReportActivity.EXTRA_REPORT,report)
                intent.putExtra(DetailReportActivity.EXTRA_REPORT_ID,id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position],listReportIds?.ids?.get(position))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}
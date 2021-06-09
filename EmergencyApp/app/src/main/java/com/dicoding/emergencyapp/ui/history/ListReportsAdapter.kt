package com.dicoding.emergencyapp.ui.history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.dicoding.emergencyapp.databinding.ItemReportBinding

class ListReportsAdapter: RecyclerView.Adapter<ListReportsAdapter.ListViewHolder>() {

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
                tvStatus.text = report?.status
                if (report?.status == "Waiting for responder"){
                    imgStatus.setImageResource(R.drawable.ic_finding_responder)
                } else if (report?.status == "Responder on progress"){
                    imgStatus.setImageResource(R.drawable.ic_on_progress)
                } else if (report?.status == "Case resolved"){
                    imgStatus.setImageResource(R.drawable.ic_resolved)
                } else {
                    imgStatus.setImageResource(R.drawable.ic_broken_image)
                }

                if (report?.classification == "Crime"){
                    imgLogo.setImageResource(R.drawable.ic_crime_logo)
                } else if (report?.classification == "Medical"){
                    imgLogo.setImageResource(R.drawable.ic_medical_logo)
                } else if (report?.classification == "Fire"){
                    imgLogo.setImageResource(R.drawable.ic_fire_logo)
                } else if (report?.classification == "Natural Disaster"){
                    imgLogo.setImageResource(R.drawable.ic_natural_disaster_logo)
                } else if (report?.classification == "Traffic Accident"){
                    imgLogo.setImageResource(R.drawable.ic_traffic_accident_logo)
                } else {
                    imgLogo.setImageResource(R.drawable.ic_unknown_logo)
                }
            }

            itemView.setOnClickListener{
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

}

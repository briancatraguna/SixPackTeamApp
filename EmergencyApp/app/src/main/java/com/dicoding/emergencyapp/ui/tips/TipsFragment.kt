package com.dicoding.emergencyapp.ui.tips

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.emergencyapp.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private lateinit var binding: FragmentTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvTraffic.setOnClickListener {
            directToDetails(DetailsTipsActivity.TRAFFIC_KEY)
        }
        binding.cvCrime.setOnClickListener {
            directToDetails(DetailsTipsActivity.CRIME_KEY)
        }
        binding.cvFire.setOnClickListener {
            directToDetails(DetailsTipsActivity.FIRE_KEY)
        }
        binding.cvLifestyle.setOnClickListener {
            directToDetails(DetailsTipsActivity.LIFESTYLE_KEY)
        }
    }

    private fun directToDetails(key: String){
        val intent = Intent(context,DetailsTipsActivity::class.java)
        intent.putExtra(DetailsTipsActivity.TOPIC_KEY,key)
        startActivity(intent)
    }

}


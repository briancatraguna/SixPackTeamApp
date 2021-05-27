package com.dicoding.emergencyapp.ui.tips

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.dicoding.emergencyapp.databinding.FragmentTipsBinding
import com.dicoding.emergencyapp.ui.tips.crime.CrimeActivity
import com.dicoding.emergencyapp.ui.tips.fire.FireActivity
import com.dicoding.emergencyapp.ui.tips.lifestyle.LifestyleActivity
import com.dicoding.emergencyapp.ui.tips.traffic.TrafficActivity
import kotlinx.android.synthetic.main.fragment_tips.view.*
import java.io.*
import java.util.*

class TipsFragment : Fragment() {

    private lateinit var binding: FragmentTipsBinding
    lateinit var trafficBtn: CardView
    lateinit var crimeBtn: CardView
    lateinit var fireBtn: CardView
    lateinit var lifeBtn: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsBinding.inflate(inflater, container, false)
        trafficBtn = binding.cvTraffic
        crimeBtn = binding.cvCrime
        fireBtn = binding.cvFire
        lifeBtn = binding.cvLifestyle
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trafficBtn.setOnClickListener {
            val intent = Intent(context, TrafficActivity::class.java)
            startActivity(intent)
        }
        crimeBtn.setOnClickListener {
            val intent = Intent(context, CrimeActivity::class.java)
            startActivity(intent)
        }
        fireBtn.setOnClickListener {
            val intent = Intent(context, FireActivity::class.java)
            startActivity(intent)
        }
        lifeBtn.setOnClickListener {
            val intent = Intent(context, LifestyleActivity::class.java)
            startActivity(intent)
        }
    }

}


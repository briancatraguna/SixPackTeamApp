package com.dicoding.emergencyapp.ui.news.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.FragmentHealthBinding

class HealthFragment : Fragment() {

    private lateinit var binding: FragmentHealthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthBinding.inflate(inflater,container,false)
        return binding.root
    }

}
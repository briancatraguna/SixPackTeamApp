package com.dicoding.emergencyapp.ui.help

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.dicoding.emergencyapp.databinding.FragmentTipsBinding
import com.dicoding.emergencyapp.ui.typing.TypingActivity
import kotlinx.android.synthetic.main.fragment_tips.view.*
import java.io.*
import java.util.*

class TipsFragment : Fragment() {

    private lateinit var binding: FragmentTipsBinding
    lateinit var hospitalBtn: CardView
    lateinit var policeBtn: CardView
    lateinit var fireBtn: CardView
    lateinit var lifeBtn: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsBinding.inflate(inflater, container, false)
        hospitalBtn = binding.cvHospital
        policeBtn = binding.cvPolice
        fireBtn = binding.cvFire
        lifeBtn = binding.cvLifestyle
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hospitalBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
        policeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
        fireBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
        lifeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
    }

}


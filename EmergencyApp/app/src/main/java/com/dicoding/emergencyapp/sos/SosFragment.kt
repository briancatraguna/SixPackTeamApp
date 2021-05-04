package com.dicoding.emergencyapp.sos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.FragmentSosBinding

class SosFragment : Fragment() {

    private lateinit var binding: FragmentSosBinding
    private lateinit var radioGroup: RadioGroup

    companion object {
        private var TAG = SosFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSosBinding.inflate(inflater,container,false)
        radioGroup = binding.radioGroup
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                R.id.ambulance ->{
                    Toast.makeText(context,"Ambulance Mode",Toast.LENGTH_SHORT).show()
                }
                R.id.police -> {
                    Toast.makeText(context,"Police Mode",Toast.LENGTH_SHORT).show()
                }
                R.id.firefighter -> {
                    Toast.makeText(context,"Firefighter Mode",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
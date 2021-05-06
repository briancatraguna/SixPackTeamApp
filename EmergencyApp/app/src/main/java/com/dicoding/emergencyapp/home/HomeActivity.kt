package com.dicoding.emergencyapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivityHomeBinding
import com.dicoding.emergencyapp.help.HelpFragment
import com.dicoding.emergencyapp.sos.SosFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val sosFragment = SosFragment()
    private val helpFragment = HelpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set sos as the starting fragment
        replaceFragment(sosFragment)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_sos -> replaceFragment(sosFragment)
                R.id.ic_help -> replaceFragment(helpFragment)
            }
            true
        }

        binding.toolbarHome.seeLocationBtn.setOnClickListener {

        }

    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}
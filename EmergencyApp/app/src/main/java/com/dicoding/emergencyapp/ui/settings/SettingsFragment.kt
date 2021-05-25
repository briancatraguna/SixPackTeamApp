package com.dicoding.emergencyapp.ui.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.recreate
import com.dicoding.emergencyapp.databinding.FragmentSettingsBinding
import com.dicoding.emergencyapp.ui.authentication.login.LoginActivity
import com.dicoding.emergencyapp.ui.guideline.GuidelineActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadLanguage()
        binding.changeLanguage.setOnClickListener {
            changeLanguage()
        }
        binding.guideline.setOnClickListener {
            val intent = Intent(context, GuidelineActivity::class.java)
            startActivity(intent)
        }

        binding.signOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    companion object;

    private fun changeLanguage() {
        val listItems = arrayOf("English", "Indonesia")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose language")
        builder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if(which == 0) {
                setLanguage("en")
                recreate(context as Activity)
            }
            else if (which == 1) {
                setLanguage("in")
                recreate(context as Activity)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setLanguage(lang: String) {
        val local = Locale(lang)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local
        context?.resources?.updateConfiguration(config, context?.resources?.displayMetrics)

        val editor = context?.getSharedPreferences("Settings", Context.MODE_PRIVATE)?.edit()
        editor?.putString("lang", lang)
        editor?.apply()
    }

    private fun loadLanguage() {
        val sharedPreferences = context?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences?.getString("lang", "")
        if (language != null) {
            setLanguage(language)
        }
    }
}
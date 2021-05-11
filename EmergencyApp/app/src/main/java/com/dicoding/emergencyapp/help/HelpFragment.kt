package com.dicoding.emergencyapp.help

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.FragmentHelpBinding
import com.dicoding.emergencyapp.guideline.GuidelineActivity
import com.dicoding.emergencyapp.home.HomeActivity
import com.dicoding.emergencyapp.typing.TypingActivity
import java.io.File
import java.io.*
import java.util.*
import java.util.jar.Manifest

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding
    private var textFile: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val saveBtn = binding.btnSave
        val typingText = binding.typingText

        saveBtn.setOnClickListener {
            saveFile()
        }
        typingText.setOnClickListener {
            moveToTyping()
        }
    }

    private fun saveFile() {
        textFile = binding.tvResult.text.toString().trim()
        val fileOutputStream: FileOutputStream
        try {
            context?.openFileOutput("TTS.txt", Context.MODE_PRIVATE).use { fileOutputStream ->
                if (fileOutputStream != null) {
                    fileOutputStream.write(textFile.toByteArray())
                    var path = context?.filesDir
                    Toast.makeText(context,"File has been saved in " + path, Toast.LENGTH_LONG).show()

                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun moveToTyping() {
        val intent = Intent(context, TypingActivity::class.java)
        context?.startActivity(intent)
    }
}


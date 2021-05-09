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
    private val RQ_SPEECH_REC = 102
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
        val recordBtn = binding.btnRecord
        val saveBtn = binding.btnSave
        val typingText = binding.typingText
        recordBtn.setOnClickListener {
            askSpeechInput()
        }

        saveBtn.setOnClickListener {
            saveFile()
        }
        typingText.setOnClickListener {
            moveToTyping()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.tvResult.text = result?.get(0).toString()
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(context)){
            Toast.makeText(context,"Speech recognition is not available",Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Explain your emergency situation!")
            startActivityForResult(i,RQ_SPEECH_REC)
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


package com.dicoding.emergencyapp.ui.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dicoding.emergencyapp.databinding.FragmentHelpBinding
import com.dicoding.emergencyapp.ui.typing.TypingActivity
import java.io.*
import java.util.*

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
        textFile = "Dummy Text"
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


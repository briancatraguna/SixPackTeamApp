package com.dicoding.emergencyapp.sos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.FragmentSosBinding
import com.dicoding.emergencyapp.guideline.GuidelineActivity
import com.dicoding.emergencyapp.typing.TypingActivity
import java.util.*

class SosFragment : Fragment() {

    private lateinit var binding: FragmentSosBinding
    private lateinit var radioGroup: RadioGroup
    private lateinit var transcription: String

    companion object {
        private var TAG = SosFragment::class.java.simpleName
        private const val RQ_SPEECH_REC = 102
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

                }
                R.id.police -> {

                }
                R.id.firefighter -> {

                }
            }
        }

        val emergencyButton = binding.sosButtonContainer
        emergencyButton.setOnClickListener {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(context,"Choose one of the emergency services!",Toast.LENGTH_SHORT).show()
            }
            else {
                askSpeechInput()
            }
        }

        val guidelineBtn = binding.guideline
        guidelineBtn.setOnClickListener {
            val intent = Intent(context, TypingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            transcription = result?.get(0).toString()
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(context)){
            Toast.makeText(context,"Speech recognition is not available",Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Explain your emergency situation!")
            startActivityForResult(i,RQ_SPEECH_REC)
        }
    }
}
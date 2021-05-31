package com.dicoding.emergencyapp.ui.sos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.FragmentSosBinding
import com.dicoding.emergencyapp.ui.sos.typing.TypingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.StorageReference
import java.util.*

class SosFragment : Fragment() {

    private lateinit var binding: FragmentSosBinding
    private lateinit var transcription: String
    private lateinit var viewModel: SosViewModel

    companion object {
        private var TAG = SosFragment::class.java.simpleName
        private const val RQ_SPEECH_REC = 102
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),ViewModelProvider.NewInstanceFactory())[SosViewModel::class.java]

        val emergencyButton = binding.sosButtonContainer
        emergencyButton.setOnClickListener {
            askSpeechInput()
        }

        val guidelineBtn = binding.guideline
        guidelineBtn.setOnClickListener {
            val intent = Intent(context, TypingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val userId = arguments?.getString("userId")
        val userPhoto = arguments?.getString("userPhoto")
        val latitude = arguments?.getDouble("lat")
        val longitude = arguments?.getDouble("long")
        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            transcription = result?.get(0).toString()
            viewModel.postData(
                    userId = userId,
                    userPhoto = userPhoto,
                    transcription = transcription,
                    report = "Empty",
                    latitude = latitude,
                    longitude = longitude,
                    status = getString(R.string.status_find)
            )
        }
        viewModel.getStatus().observe(requireActivity(),{
            if (!it.isEmpty()){
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })
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
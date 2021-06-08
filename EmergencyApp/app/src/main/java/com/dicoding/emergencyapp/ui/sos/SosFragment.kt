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
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.remote.FirebaseDataSource
import com.dicoding.emergencyapp.data.remote.NerDataSource
import com.dicoding.emergencyapp.data.repository.FirebaseRepository
import com.dicoding.emergencyapp.data.repository.NerRepository
import com.dicoding.emergencyapp.databinding.FragmentSosBinding
import com.dicoding.emergencyapp.helpers.ClassificationAlgorithm
import com.dicoding.emergencyapp.helpers.DateHelper
import com.dicoding.emergencyapp.helpers.NerResponseParser
import com.dicoding.emergencyapp.ui.sos.typing.TypingActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class SosFragment : Fragment() {

    private lateinit var binding: FragmentSosBinding
    private lateinit var transcription: String
    private lateinit var viewModel: SosViewModel
    private lateinit var nerViewModel: NerViewModel

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
        viewModel = SosViewModel(FirebaseRepository(FirebaseDataSource()))
        nerViewModel = NerViewModel(NerRepository(NerDataSource.getInstance()))

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
        val userPhoto = arguments?.getString("userPhoto").toString()
        val usersName = arguments?.getString("usersName").toString()
        val latitude = arguments?.getDouble("lat")
        val longitude = arguments?.getDouble("long")
        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            transcription = result?.get(0).toString()
        }

        val classificationObject = ClassificationAlgorithm(transcription)
        val classifiedClass = classificationObject.getClass()

        nerViewModel.getResults(transcription).observe(this,{ response ->
            val parser = NerResponseParser(response)
            val report = parser.getStringFormat()
            viewModel.uploadData(
                usersName,
                userPhoto,
                DateHelper.getDate(),
                userId,
                transcription,
                report,
                classifiedClass,
                latitude,
                longitude,
                "Waiting for responder"
            )
        })
        viewModel.getStatus().observe(requireActivity(),{
            if (it){
                Toast.makeText(context,"Data succesfully uploaded. Waiting for responder.",Toast.LENGTH_SHORT).show()
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
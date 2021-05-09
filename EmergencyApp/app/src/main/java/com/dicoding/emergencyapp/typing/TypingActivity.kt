package com.dicoding.emergencyapp.typing

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.emergencyapp.R
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding.widget.RxTextView
import java.util.*
import io.reactivex.Observable

class TypingActivity : AppCompatActivity() {
    private lateinit var nameEdit: EditText
    private lateinit var locationEdit: EditText
    private lateinit var situationEdit: EditText
    private lateinit var nameInput: TextInputLayout
    private lateinit var locationInput: TextInputLayout
    private lateinit var situationInput: TextInputLayout
    private lateinit var sendButton: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typing)

        nameEdit = findViewById(R.id.name_edit)
        locationEdit = findViewById(R.id.location_edit)
        situationEdit = findViewById(R.id.situation_edit)
        nameInput = findViewById(R.id.name_input)
        locationInput = findViewById(R.id.location_edit)
        situationInput = findViewById(R.id.situation_edit)
        sendButton = findViewById(R.id.send_btn)

        nameInput.error = "Require name"
        sendButtonObservable()
    }

    private fun sendButtonObservable() : Observable<String> {
        return Observable.create { emmiter ->
            sendButton.setOnClickListener {
                emmiter.onNext(nameEdit.text.toString())
                Toast.makeText(applicationContext, "Report has been send", Toast.LENGTH_LONG)
            }
            emmiter.setCancellable {
                sendButton.setOnClickListener(null)
            }
        }
    }
}
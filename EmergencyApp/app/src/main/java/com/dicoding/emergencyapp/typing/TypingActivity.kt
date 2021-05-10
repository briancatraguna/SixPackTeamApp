package com.dicoding.emergencyapp.typing

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.emergencyapp.R
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
import java.util.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

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

        sendButton.setOnClickListener {
            clickSendButton()
        }

    }

    private fun clickSendButton() {
        if(nameEdit != null) {
            RxTextView.afterTextChangeEvents(nameEdit)
                    .skipInitialValue()
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
        else {
            nameInput.error = "Require name"
        }

        if(locationEdit != null) {
            RxTextView.afterTextChangeEvents(locationEdit)
                    .skipInitialValue()
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
        else {
            nameInput.error = "Require location"
        }

        if(situationEdit != null) {
            RxTextView.afterTextChangeEvents(locationEdit)
                    .skipInitialValue()
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
        else {
            situationInput.error = "Require situation"
        }

    }

    private fun getObserver() : Observer<String> {
        return object: Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG", "On Subscribe")
            }

            override fun onNext(t: String) {
                Log.d("TAG", "On Next $t")
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "On Error" + e.message)
            }

            override fun onComplete() {
                Log.d("TAG", "On Complete")
            }

        }
    }

    private fun getObservable() : Observable<EditText> {
        return Observable.just(nameEdit, locationEdit, situationEdit)
    }
}
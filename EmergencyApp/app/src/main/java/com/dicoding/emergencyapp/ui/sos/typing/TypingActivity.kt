package com.dicoding.emergencyapp.ui.sos.typing

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.remote.ClassificationDataSource
import com.dicoding.emergencyapp.data.remote.FirebaseDataSource
import com.dicoding.emergencyapp.data.remote.NerDataSource
import com.dicoding.emergencyapp.data.repository.ClassificationRepository
import com.dicoding.emergencyapp.data.repository.FirebaseRepository
import com.dicoding.emergencyapp.data.repository.NerRepository
import com.dicoding.emergencyapp.databinding.ActivityTypingBinding
import com.dicoding.emergencyapp.helpers.DateHelper
import com.dicoding.emergencyapp.helpers.NerResponseParser
import com.dicoding.emergencyapp.ui.home.HomeActivity
import com.dicoding.emergencyapp.ui.sos.ClassificationViewModel
import com.dicoding.emergencyapp.ui.sos.NerViewModel
import com.dicoding.emergencyapp.ui.sos.SosViewModel
import com.google.android.gms.location.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class TypingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypingBinding
    private lateinit var viewModel: SosViewModel
    private lateinit var mAuth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var mRootStorage: StorageReference
    private var latitude: Double? = 0.0
    private var longitude: Double? = 0.0
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var nerViewModel: NerViewModel
    private lateinit var classificationViewModel: ClassificationViewModel

    companion object {
        //Unique int for permission ID
        private const val PERMISSION_ID = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = SosViewModel(FirebaseRepository(FirebaseDataSource()))
        nerViewModel = NerViewModel(NerRepository(NerDataSource.getInstance()))
        classificationViewModel = ClassificationViewModel(ClassificationRepository(
            ClassificationDataSource.getInstance()))

        mRootStorage = FirebaseStorage.getInstance().getReference()
        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        val transcription = binding.situationEdit.text.toString()

        val userId = user?.uid.toString()
        val userName = user?.displayName.toString()
        val userPhoto = user?.photoUrl.toString()

        binding.sendBtn.setOnClickListener {
            var report = ""
            var classification = ""
            nerViewModel.getResults(transcription).observe(this,{ nerResponse ->
                val parser = NerResponseParser(nerResponse)
                report = parser.getStringFormat()
                classificationViewModel.getResults(transcription).observe(this,{ classificationResponse->
                    classification = classificationResponse.label.toString()
                    if (report!=""&&classification!=""){
                        viewModel.uploadData(
                            userName,
                            userPhoto,
                            DateHelper.getDate(),
                            userId,
                            transcription,
                            report,
                            classification,
                            latitude,
                            longitude,
                            "Waiting for responder"
                        )
                    }
                })
            })
            viewModel.getStatus().observe(this,{
                if (it){
                    Toast.makeText(this,"Data succesfully uploaded. Waiting for responder.",
                        Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.buttonBack.setOnClickListener { finish() }
    }

    private fun getLastLocation(){
        if(checkPermission()){
            if (isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task->
                    var location: Location? = task.result
                    if (location == null){
                        getNewLocation()
                    } else {
                        latitude = location.latitude
                        longitude = location.longitude
                    }
                }
            } else {
                Toast.makeText(this,"Please enable your location service", Toast.LENGTH_SHORT).show()
            }
        } else {
            RequestPermission()
        }

    }

    private fun getNewLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation
            latitude = lastLocation.latitude
            longitude = lastLocation.longitude
        }
    }

    private fun checkPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun RequestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),
            TypingActivity.PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

}
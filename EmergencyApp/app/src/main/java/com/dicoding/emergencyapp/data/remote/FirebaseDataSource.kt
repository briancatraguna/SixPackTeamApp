package com.dicoding.emergencyapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.emergencyapp.data.entity.ReportEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDataSource {

    private val database = Firebase.database
    private val myRef = database.getReference("users")

    private var _uploadStatus = MutableLiveData<Boolean>()
    var uploadStatus: LiveData<Boolean> = _uploadStatus

    fun uploadData(
        timestamp: String,
        userId: String?,
        transcription: String,
        report: String,
        latitude: Double?,
        longitude: Double?,
        status: String
    ){
        val userRef = myRef.child(userId.toString())
        val reportRef = userRef.push().key.toString()
        val report = ReportEntity(
            timestamp,
            transcription,
            report,
            latitude,
            longitude,
            status
        )
        userRef.child(reportRef).setValue(report).addOnCompleteListener {
            _uploadStatus.value = it.isSuccessful
        }
    }

    fun readUserReports(userId: String?): MutableLiveData<List<ReportEntity?>> {
        val userRef = myRef.child(userId.toString())
        val result = MutableLiveData<List<ReportEntity?>>()
        val reportList = arrayListOf<ReportEntity?>()
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (report in snapshot.children){
                        val reportObject = report.getValue(ReportEntity::class.java)
                        reportList.add(reportObject)
                    }
                    result.value = reportList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return result
    }

}
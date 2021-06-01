package com.dicoding.emergencyapp.ui.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.databinding.ActivityEditProfileBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var mAuth: FirebaseAuth
    private var user: FirebaseUser? = null

    private lateinit var mRootStorage: StorageReference

    private var localFileUri: Uri? = null
    private lateinit var serverFileUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCancel.setOnClickListener {
            finish()
        }

        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
        100)

        mRootStorage = FirebaseStorage.getInstance().reference
        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser

        binding.edittextName.setText(user?.displayName)
        val photoUri: Uri? = user?.photoUrl
        if (photoUri!=null){
            Glide.with(this)
                .load(photoUri)
                .placeholder(R.drawable.default_profile_picture)
                .error(R.drawable.default_profile_picture)
                .into(binding.imgProfilePicture)
        }

        binding.tvChangePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,101)
        }

        binding.tvDone.setOnClickListener {
            if (binding.edittextName.textAlignment.toString().trim().equals("")){
                binding.edittextName.error = "Enter name"
            } else {
                updateNameAndPhoto()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==101){
            if (resultCode== RESULT_OK){
                localFileUri = data?.data
                binding.imgProfilePicture.setImageURI(localFileUri)
            }
        }
    }

    private fun updateNameAndPhoto(){
        binding.progressBar.visibility = View.VISIBLE
        val fileName: String = user?.uid + ".jpg"
        val fileRef: StorageReference = mRootStorage.child("images/"+fileName)
        fileRef.putFile(localFileUri!!)
            .addOnSuccessListener(OnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener {
                    serverFileUri = it
                    val request = UserProfileChangeRequest.Builder()
                        .setDisplayName(binding.edittextName.text.toString())
                        .setPhotoUri(serverFileUri)
                        .build()

                    user?.updateProfile(request)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this,"Profile updated succesfully",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,"Failed to update: ${it.exception}",Toast.LENGTH_SHORT).show()
                        }
                        binding.progressBar.visibility = View.GONE
                        finish()
                    }
                }
            })
    }
}
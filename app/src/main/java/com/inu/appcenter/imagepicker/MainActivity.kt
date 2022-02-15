package com.inu.appcenter.imagepicker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private var PICK_IMAGE_FROM_ALBUM = 0
    private var photoUri : Uri? = null
    private lateinit var getResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv = findViewById<ImageView>(R.id.imageView)

        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        //startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                //사진을 선택했을 때, 이미지의 경로가 넘어오게 된다.
                photoUri = it.data?.data
                iv.setImageURI(photoUri)
            }else{
                finish()
            }
        }
        getResult.launch(photoPickerIntent)
    }
}
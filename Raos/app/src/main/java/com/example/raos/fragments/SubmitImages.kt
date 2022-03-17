package com.example.raos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.raos.R
import com.example.raos.Utils.FileUtil
import com.example.raos.retrofit.ImageBuilder
import com.example.raos.retrofit.ImageInterface
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_submit_images.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class SubmitImages : Fragment() {
    var files: MutableList<Uri> = ArrayList()
    var files2: MutableList<Uri> = ArrayList()
    var files3: MutableList<Uri> = ArrayList()
    var files4: MutableList<Uri> = ArrayList()
    var files5: MutableList<Uri> = ArrayList()
    var files6: MutableList<Uri> = ArrayList()


    val fileUtil = FileUtil()

    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    private val FRONT_CAPTURE_ID_IMAGE = 234
    private val  FRONT_PICK_ID_IMAGE = 345
    private val BACK_CAPTURE_ID_IMAGE = 456
    private val  BACK_PICK_ID_IMAGE = 567
    private val CAPTURE_PASSPORT_IMAGE = 678
    private val  PICK_PASSPORT_IMAGE = 789

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_submit_images, container, false)

        val front_capture_id_btn = view.findViewById<MaterialButton>(R.id.front_capture_id_btn)
        val front_pick_id_btn = view.findViewById<MaterialButton>(R.id.front_pick_id_btn)
        val back_capture_id_btn = view.findViewById<MaterialButton>(R.id.back_capture_id_btn)
        val back_pick_id_btn = view.findViewById<MaterialButton>(R.id.back_pick_id_btn)
        val capture_passport_btn = view.findViewById<MaterialButton>(R.id.capture_passport_btn)
        val pick_passport_btn = view.findViewById<MaterialButton>(R.id.pick_passport_btn)
        val next_btn = view.findViewById<MaterialButton>(R.id.next_btn)


        front_capture_id_btn.setOnClickListener {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, FRONT_CAPTURE_ID_IMAGE)
        }
        front_pick_id_btn.setOnClickListener {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto, FRONT_PICK_ID_IMAGE) //one can be replaced with any action code
        }
        back_capture_id_btn.setOnClickListener {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, BACK_CAPTURE_ID_IMAGE)
        }
        back_pick_id_btn.setOnClickListener {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto, BACK_PICK_ID_IMAGE) //one can be replaced with any action code
        }
        capture_passport_btn.setOnClickListener {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, CAPTURE_PASSPORT_IMAGE)
        }
        pick_passport_btn.setOnClickListener {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto, PICK_PASSPORT_IMAGE) //one can be replaced with any action code
        }


        next_btn.setOnClickListener {
            uploadImages()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != AppCompatActivity.RESULT_CANCELED) {
            when (requestCode) {
                FRONT_CAPTURE_ID_IMAGE -> if (resultCode == AppCompatActivity.RESULT_OK && data != null ) {
                    val img = data.extras?.get("data") as Bitmap

                    front_id_image.setImageBitmap(img)
                    val imgPath = img.let {
                        this.context?.let { it1 ->
                            getImageUri(it1, it).let { fileUtil.getPath(this.context, it)
                            }
                        }
                    }

                    files.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }
                }
                BACK_CAPTURE_ID_IMAGE ->if (resultCode == AppCompatActivity.RESULT_OK && data != null ) {
                    val img = data.extras?.get("data") as Bitmap
                    back_id_image.setImageBitmap(img)
                    val imgPath = img.let {
                        this.context?.let { it1 ->
                            getImageUri(it1, it).let { fileUtil.getPath(this.context, it)
                            }
                        }
                    }

                    files2.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }
                }

                CAPTURE_PASSPORT_IMAGE -> if (resultCode == AppCompatActivity.RESULT_OK && data != null ) {
                    val img = data.extras?.get("data") as Bitmap
                    passport_image.setImageBitmap(img)
                    val imgPath = img.let {
                        this.context?.let { it1 ->
                            getImageUri(it1, it).let { fileUtil.getPath(this.context, it)
                            }
                        }
                    }

                    files3.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }
                }


                FRONT_PICK_ID_IMAGE -> if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
                    val img = data.data!!
                    front_id_image.setImageURI(img)
                    val imgPath = img.let { fileUtil.getPath(this.context, it) }

                    files4.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }
                }

                BACK_PICK_ID_IMAGE -> if (resultCode == AppCompatActivity.RESULT_OK && data !=null) {
                    val img = data.data!!
                    back_id_image.setImageURI(img)
                    val imgPath = img.let { fileUtil.getPath(this.context, it) }

                    files5.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }

                }

                PICK_PASSPORT_IMAGE -> if (resultCode == AppCompatActivity.RESULT_OK && data !=null) {
                    val img = data.data!!
                    passport_image.setImageURI(img)
                    val imgPath = img.let { fileUtil.getPath(this.context, it) }

                    files6.add(Uri.parse(imgPath))
                    if (imgPath != null) {
                        Log.e("image", imgPath)
                    }
                }

            }
        }
    }

    //===== bitmap to Uri
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            "intuenty",
            null
        )
        Log.d("image uri", path)
        return Uri.parse(path)
    }

    private fun prepareFilePart(partName: String, fileUri: Uri): MultipartBody.Part {

        val file = File(fileUri.getPath())
        Log.i("here is error", file.getAbsolutePath())
        // create RequestBody instance from file
        val requestFile: RequestBody = RequestBody.create(
            "image/*".toMediaTypeOrNull(),
            file
        )

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
//                Toast.makeText(this@MainActivity, "Permission denied", Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    //===== Upload files to server
    fun uploadImages() {
        uploadFrontCapture()
        uploadBackCapture()
        uploadPassportCapture()

        uploadFrontPick()
        uploadBackPick()
        uploadPassportPick()
    }
    private fun uploadFrontCapture(){
        if(files.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }
    }
    private fun uploadBackCapture(){
        if(files2.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files2) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
                        Log.e(" response body: ",result.toString())
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }
    }
    private fun uploadPassportCapture(){
        if (files3.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files3) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }
    }
    private fun uploadFrontPick(){
        if(files4.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files4) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }
    }
    private fun uploadBackPick(){
        if(files5.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files5) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }
    }
    private fun uploadPassportPick(){
        if (files6.isNotEmpty()){
            val list: MutableList<MultipartBody.Part?> =  ArrayList()

            for (uri:Uri in files6) {
                uri.getPath()?.let { Log.i("uris", it) }
                list.add(prepareFilePart("files", uri))
            }
            val user_id = RequestBody.create("number".toMediaTypeOrNull(), "1")
            val service = ImageBuilder.buildService(ImageInterface::class.java)
            val req = service.uploadImageFile(list,user_id)

            req.enqueue(object:retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.message?.let { Log.e("Upload error:", it) }

                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.v("Upload", "success")
                    if (response.isSuccessful){
                        val result = response.body()
//                       Toast.makeText(this@MainActivity, "Files uploaded successfully", Toast.LENGTH_SHORT).show();
                    }else{
//                       Toast.makeText(this@MainActivity, "Files failed successfully", Toast.LENGTH_SHORT).show();
                    }
                }

            })
        }

    }

}
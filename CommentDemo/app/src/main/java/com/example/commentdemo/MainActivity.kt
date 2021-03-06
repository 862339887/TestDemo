package com.example.commentdemo

import android.app.Activity
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ImageSpan
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.example.commentdemo.model.CommentModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val REQUEST_PIC_ALBUM = 1
    private val REQUEST_PIC_CAMERA = 2
    private val REQUEST_PERMISSION_ALBUM=101
    private val REQUEST_PERMISSION_CAMERA=102
    private var mCameraUri :Uri?=null
    private var photoPath:String?=""
    private var imagePath:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            correct_comment_displayview.setListener(object :OnCommentClickListener{

            override fun onSendComment() {
                Toast.makeText(correct_comment_displayview.context,"111111",Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "onSendComment: "+11111111111 )
            }

        initView()
    }

    private fun initView() {
        comment_bt.setOnClickListener {


            comment_bt.isVisible = false
            comment_action_fl.isVisible = true
        }

        pic_ic.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){
                openAlbum()
            }else{
                //请求相册权限
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_PERMISSION_ALBUM)
            }
        }

        camera_ic.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)==
                    PackageManager.PERMISSION_GRANTED){
                openCamera()
            }else{
                // 请求相机权限
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CAMERA),REQUEST_PERMISSION_CAMERA)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            when (resultCode) {
                Activity.RESULT_OK -> {
                    when (requestCode) {
                        REQUEST_PIC_CAMERA ->{
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
//                                val lp = LinearLayout.LayoutParams(
//                                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                )
//                                val imageView = ImageView(this)
//                                imageView.layoutParams=lp
//
//                                imageView.setImageURI(mCameraUri)
//                                input_content_ll.addView(imageView)
                                insertImage(imagePath!!)

                            }else{
                                val lp = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                                val imageView = ImageView(this)
                                imageView.layoutParams=lp
                                imageView.setImageResource(R.drawable.ic_action_camera)
                                imageView.setImageURI(mCameraUri)
                                input_content_ll.addView(imageView)
                            }
                        }
                        REQUEST_PIC_ALBUM->{
                            if(Build.VERSION.SDK_INT>=19){
                                if (data != null) {
                                    photoPath=handleImageOnKitKat(data,this)
                                }
                            }else{
                                if (data != null) {
                                    photoPath=handleImageBeforeKitKat(data,this)
                                }

                            }
                            val lp = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            val imageView = ImageView(this)
                            imageView.layoutParams=lp
                            imageView.setImageBitmap(BitmapFactory.decodeFile(photoPath))
                            input_content_ll.addView(imageView)
                        }
                        }
                    }
                }
            }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(requestCode==REQUEST_PERMISSION_CAMERA){
                openCamera()
            }else{
                openAlbum()
            }
        }else{
            Toast.makeText(this, "用户拒绝了此权限", Toast.LENGTH_SHORT).show()
        }
    }


    private fun openAlbum(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_PIC_ALBUM)

    }

    private fun insertImage(path:String){
        val tagPath = "<img src=\"${path}\"/>"
        val ss=getSpannerString(tagPath,path!!)
        insertPhotoToEditText(ss)
        comment_content_et.append("\n")
    }

    private fun getSpannerString(tagPath:String,realPath:String):SpannableString {
        val options=BitmapFactory.Options()
        options.inSampleSize=4
        val bm=BitmapFactory.decodeFile(realPath,options)
        val ss =SpannableString(tagPath)
//        val dw=BitmapDrawable(bm)
        //是否有bitmap转换成drawable的方法
        val imageSpan= ImageSpan(this,bm)
        ss.setSpan(imageSpan,0,tagPath.length,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }

    private fun insertPhotoToEditText(ss:SpannableString){
        val editable=comment_content_et.text
        val start=comment_content_et.selectionStart
        editable.insert(start,ss)
        comment_content_et.text=editable
    }

    private fun uriToPath(uri:Uri?):String{
        val cursor =contentResolver.query(uri!!,null,null,null,null)
        cursor!!.moveToFirst()
        return cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
    }

    private fun openCamera(){
        var photoUri:Uri?=null
        var photoFile:File? =null

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
            //Android 10以上
            photoUri=createImageUri()
            imagePath=uriToPath(photoUri)
        }else{
            photoFile=createImageFile()
            if (photoFile != null) {
                photoPath=photoFile.absolutePath
            }

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                photoUri=FileProvider.getUriForFile(this,packageName+".fileProvider", photoFile!!)
            }else{
                photoUri=Uri.fromFile(photoFile)
            }
        }
        mCameraUri=photoUri
        Log.e("MainActivity", "openCamera: "+mCameraUri!!.path )
        if(photoUri!=null){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, REQUEST_PIC_CAMERA)
        }
    }

    private fun createImageUri(): Uri? {
        return if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            //有sdk 存到sdk
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        }else{
    //            没有sdk 存到内存
            contentResolver.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI,ContentValues())
        }
    }

    private fun createImageFile():File?{
        val imageName=SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir != null) {
            if(!storageDir.exists()){
                storageDir.mkdir()
            }
        }
        return File(storageDir,imageName)
    }

    private fun handleImageOnKitKat(data: Intent, context: Context?): String? {
        var imagePath: String? = null
        val uri = data.data
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                var contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), docId.toLong())
                imagePath = getImagePath(contentUri, null)
            } else if ("com.android.providers.downloads.documents" == uri.authority) {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), docId.toLong())
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.path
        }
        return imagePath
    }

    /**
     * 4.4版本以前，直接获取真实路径
     * @param data
     * @return
     */
    private fun handleImageBeforeKitKat(data: Intent, context: Context?): String? {
        val uri=data.data

        return getImagePath(uri!!,null)
    }

    /**
     * 查询图库中是否存在有指定路径的图片
     * @param uri:路径URI
     * @param selection:筛选条件
     * @param context
     * @return
     */

    private fun getImagePath(uri: Uri, selection: String?): String? {
        var path: String? = null
        // 通过Uri和selection来获取真实的图片路径
        val cursor =
            contentResolver.query(uri, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }
}

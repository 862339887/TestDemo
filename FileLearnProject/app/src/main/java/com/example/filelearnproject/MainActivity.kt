package com.example.filelearnproject

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var dbHelper: MyDateBaseHelper?=null
    private  var db:SQLiteDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add.setOnClickListener {
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,1)
        }
         //initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap= data?.extras!!.get("data")
        image_iv.setImageBitmap(bitmap as Bitmap?)
//        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri!!))


    }
    private fun initView(){
        dbHelper=MyDateBaseHelper(this,"school",1)
        create.setOnClickListener {
            db=if(dbHelper==null){
                MyDateBaseHelper(this,"school",1).writableDatabase
            }else{
                dbHelper!!.writableDatabase
            }
        }

        add.setOnClickListener {
            val contentValues=ContentValues()
//            contentValues.put("id","8")
            contentValues.put("name","李浩")
            contentValues.put("address","字节跳动")
            if(db==null){
                Toast.makeText(this,"数据库不存在",Toast.LENGTH_LONG).show()
            }else{
               val index= db!!.insert("student",null,contentValues)
            }
        }

        delete.setOnClickListener {
            if(db==null){
                Toast.makeText(this,"数据库不存在",Toast.LENGTH_LONG).show()
            }else{
                db!!.delete("student","name = ? or address=?", arrayOf("周鑫","同济国康公寓18栋"))
            }
        }

        update.setOnClickListener {
            val contentValues=ContentValues()
//            contentValues.put("id",9)
            contentValues.put("name","启龙强")
            contentValues.put("address","vivo")
            if(db==null){
                Toast.makeText(this,"数据库不存在",Toast.LENGTH_LONG).show()
            }else{
                db!!.update("student",contentValues,"id>=? and id<=?", arrayOf("8","12") )
            }
        }

        search.setOnClickListener {
            val selection="name= ?  and name=?"
            val str= arrayOf("李浩","周鑫")
            val list=ArrayList<String>()
            if(db==null){
                Toast.makeText(this,"数据库不存在",Toast.LENGTH_LONG).show()
            }else{
                val cursor=db!!.query("student",null,selection,str,null,null,null)
                if(cursor.moveToFirst()){
                    do {
                        val id = cursor.getString(cursor.getColumnIndex("id"))
                        list.add(id)
                    }while (cursor.moveToNext())
                }
                cursor.close()
            }
        }
    }
}
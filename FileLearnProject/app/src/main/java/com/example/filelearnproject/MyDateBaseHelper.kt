package com.example.filelearnproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDateBaseHelper(context: Context, name:String, version:Int) : SQLiteOpenHelper(context,name,null,version) {

    companion object{
        const val TAG="MyDateBaseHelper"
        const val sqlCreate="create table student(id integer primary key autoincrement," +
                "name text," +
                "address char(1))"
        const val updateAge="alter table student add column age Integer"
        const val updateGender="alter table student add column gender text"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if(db==null){
            Log.e(TAG, "onCreate: "+"数据库创建失败，请重试" )
        }else{
            db.execSQL(sqlCreate)
//            db.execSQL(sqlCreate2)

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion!=newVersion){
            when(newVersion){
                1->{
                    db!!.execSQL(sqlCreate)
                }
                2->{
                    db!!.execSQL(updateAge)
                }else->{
                    db!!.execSQL(updateGender)
                }
            }
        }
    }
}
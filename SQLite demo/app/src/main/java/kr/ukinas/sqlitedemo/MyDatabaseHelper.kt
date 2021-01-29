package kr.ukinas.sqlitedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "ALPHABLOG.sqlite",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) { // 데이터베이스 초기설정
        db?.execSQL("CREATE TABLE POSTS(_id integer primary key autoincrement, TITLE TEXT, BODY TEXT)")
        db?.execSQL("INSERT INTO POSTS(TITLE, BODY) VALUES('First post title','Body of first post')")
 //       for (i in 1 until 21) {
 //           db?.execSQL("INSERT INTO POSTS(TITLE, BODY) VALUES('Post number: $i','Body of post number: $i')")
 //       }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}
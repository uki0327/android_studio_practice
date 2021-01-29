package kr.ukinas.sqlitetest2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.prefs.PreferencesFactory

class MainActivity : AppCompatActivity() {
    lateinit var myHelper: MyDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInit = findViewById<Button>(R.id.btnInit)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnSelect = findViewById<Button>(R.id.btnSelect)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val editName = findViewById<TextView>(R.id.editName)
        val editNumber = findViewById<TextView>(R.id.editNumber)
        val etName= findViewById<TextView>(R.id.etName)
        val etNumber= findViewById<TextView>(R.id.etNumber)

        myHelper = MyDBHelper(this, "groundDB", null, 1)

        btnInit.setOnClickListener {
            sqlDB = myHelper.writableDatabase //쓰기 가능 모드
//            / 초기화 버튼을 누른다면 db 생성
            myHelper.onUpgrade(sqlDB, 1, 2) //테이블  생성.
        }

        btnInsert.setOnClickListener {//레코드 추가
            sqlDB.execSQL( //문자열은 ''처리, 숫자는 그냥 씀
                    "insert into groupTBL values('"
                            + editName.text.toString() + "',"
                            + editNumber.text.toString() + ");"
            )//
            Toast.makeText(applicationContext, "${editName.text.toString()}, " +
                    "${editNumber.text.toString()}입력됨",Toast.LENGTH_SHORT).show()
            //입력 안내 토스트 메세지 출력
        }

        btnSelect.setOnClickListener {
            sqlDB = myHelper.readableDatabase
            val c: Cursor
            var strName : String = ""
            var strNum : String = ""
            c = sqlDB.rawQuery("select * from groupTBL;", null)

            while (c.moveToNext()){
                strName += c.getString(0) + "\n"
                strNum += c.getString(1) + "\n"

            }
            etName.setText(strName)
            etNumber.setText(strNum)

            c.close()
        }

        btnUpdate.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL(
                    "update groupTBL set gNumber="
                            + editNumber.text.toString() + " where gName='"
                            + editName.text.toString() + "';"
            )//테이블의 gname이 pk역할.
        }

        btnDelete.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL(
                    "delete from groupTBL where (gName = '"
                            + editName.text.toString() + "');"
            )
        }

    }


    class MyDBHelper(context: Context?, name:String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
        : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table groupTBL (gname char(20), gnumber integer);")


        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("drop table if exists groupTBL")
            //테이블이 있다면 삭제

            onCreate(db)
            //테이블 생성
        }


    }

    override fun onDestroy() { // 액티비티가 소멸될 때(즉 프로그램 종료 시)
        sqlDB.close() // 사용했던 db 닫기
        super.onDestroy()
    }
}
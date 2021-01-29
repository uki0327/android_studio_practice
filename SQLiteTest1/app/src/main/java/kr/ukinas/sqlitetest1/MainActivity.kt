package kr.ukinas.sqlitetest1

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var databaseName: String
    lateinit var tableName: String
    lateinit var status: TextView
    var databaseCreated = false
    var tableCreate = false
    var db: SQLiteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "sqlite-test-kotlin"

        val databaseNameInput = findViewById<EditText>(R.id.dbET)
        val tableNameInput = findViewById<EditText>(R.id.tblET)
        val createDatabaseBtn = findViewById<Button>(R.id.createDB)
        val createTableBtn = findViewById<Button>(R.id.createTable)
        val selectBtn = findViewById<Button>(R.id.btnSelect)
        status = findViewById<TextView>(R.id.status)

        createDatabaseBtn.setOnClickListener {
            databaseName = databaseNameInput.text.toString()
            createDatabase(databaseName)
        }

        createTableBtn.setOnClickListener {
            tableName = tableNameInput.text.toString()
            createDatabaseFun(tableName)
        }

        selectBtn.setOnClickListener {
            select()
        }
    }

    fun select() {
        if (db != null) { //c1은 커서 레코드를 1줄씩 읽을 때 이동하여 가르킴
            val c1 = db!!.rawQuery( //쿼리문에 따라 사용하는 메소드 다름
                //update와 delete에 맞는 메소드를 찾아라.
                "select * from $tableName"
                , null
            )
            val recordCount = c1.count //총 레코드 개수
            for (i in 0 until recordCount) { // 레코드 갯수 만큼 순환
                c1.moveToNext() //커서를 다음 레코드로
                val name = c1.getString(1) //인덱스 0 부터 시작
                val age = c1.getInt(2) // 2번째 항목은 나이
                val phone = c1.getString(3) // 3은 전화번호
                println((i).toString() + ":" + name + "," + age + "," + phone)
            }
            c1.close() // 사용 다 하고 커서 닫기
        } else {
            println("table is null")
        }
    }

    fun createDatabaseFun(name: String) {
        if (db != null) {
            db!!.execSQL(
                "create table if not exists"
                        + name
                        + "(" + " _id integer PRIMARY KEY autoincrement, "
                        + " name text, "
                        + " age integer, "
                        + " phone text); "
            )
            println(
                "create table()"
            ) // 테이블 구조에 대한 정의
            insertRecord(name)
        } else {
            println("not table")
        }
    }

    companion object {
        val DATABASE_FILE_PATH =
            Environment.getExternalStorageDirectory().absolutePath

    }

    fun insertRecord(name: String) {
        db!!.execSQL(
            "insert into $name(name, age, phone) values " +
                    "('super', 30, '010-123-4567');"
        )
    }

    fun println(msg: String) {
        status.append("\n" + msg)
    }


    private fun createDatabase(name: String) {
        try {
            db = openOrCreateDatabase( //디비가 있으면 열고 없으면 생성.
                DATABASE_FILE_PATH + File.separator + name, //sd에 지정
                //name만 쓰면 어플 설치 경로에 저장하게 됨.
                Activity.MODE_PRIVATE, null
                //현재 어플에서만 디비 사용 모드
            )
            println("is created")
        } catch (ex: Exception) {
            println("not created")
        }


    }
}
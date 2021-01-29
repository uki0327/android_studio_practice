package kr.ukinas.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kr.ukinas.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
//       var editor = sharedPreferences.edit();
//       editor.putString("USERNAME", "Justin")
//       editor.putString("LANGUAGE", "English")
//       editor.putBoolean("ISSET", false)
//       editor.apply()

        val username = sharedPreferences.getString("USERNAME", "Not set")


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 메인 레이아웃 배치

        val allPostsView = binding.allPosts // 아답터를 넣을 뷰(레이아웃)

        var dbHelper = MyDatabaseHelper(applicationContext)
        var db = dbHelper.readableDatabase // 데이터베이스 헬퍼 클래스로 SQLite DB 초기화

        var queryResults = db.rawQuery("SELECT * FROM POSTS", null) // 쿼리
        var adapter = SimpleCursorAdapter(  // 쿼리 데이터를 아답터에 등록
            applicationContext, //콘텍스트, 레이아웃 템플릿, 쿼리결과, 쿼리결과 리스트화, (int) 리스트 인덱스, 플래그(0)
            android.R.layout.simple_expandable_list_item_2, // 안드로이드 기본제공 템플릿 (2 항목)
            queryResults,
            arrayOf("TITLE", "BODY"),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )
        allPostsView.adapter = adapter // 레이아웃 뷰에 아답터 등록

        val postTitle = binding.enterTitle
        val postBody = binding.postBody
        val updateButton = binding.buttonUpdatePost
        val deleteButton = binding.buttonDeletePost
        val createButton = binding.buttonCreatePost
        val resetButton = binding.buttonReset

        createButton.setOnClickListener {
            var postDetails = ContentValues()
            postDetails.put("TITLE", postTitle.text.toString())
            postDetails.put("BODY",postBody.text.toString())
            db.insert("POSTS", null, postDetails)
            queryResults = db.rawQuery("SELECT * FROM POSTS", null)
            adapter.changeCursor(queryResults)
            postTitle.setText("")
            postBody.setText("")
            postTitle.requestFocus()

            Toast.makeText(this, "A new post created",Toast.LENGTH_SHORT).show();
        }

        resetButton.setOnClickListener {
            postTitle.setText("")
            postBody.setText("")
            postTitle.requestFocus()

            Toast.makeText(this, "CLEAR",Toast.LENGTH_SHORT).show();

            updateButton.isClickable = false
            updateButton.isEnabled = false
            deleteButton.isClickable = false
            deleteButton.isEnabled = false
            createButton.isClickable = true
            createButton.isEnabled = true
        }

        allPostsView.setOnItemClickListener { parent, view, position, id ->
            postTitle.setText(queryResults.getString(1))
            postBody.setText(queryResults.getString(2))

            updateButton.isClickable = true
            updateButton.isEnabled = true
            deleteButton.isClickable = true
            deleteButton.isEnabled = true
            createButton.isClickable = false
            createButton.isEnabled = false

            updateButton.setOnClickListener {
                var postDetails = ContentValues()
                postDetails.put("TITLE", postTitle.text.toString())
                postDetails.put("BODY",postBody.text.toString())
                db.update("POSTS", postDetails, "_id=?", arrayOf(queryResults.getString(0)))
                queryResults = db.rawQuery("SELECT * FROM POSTS", null)
                adapter.changeCursor(queryResults)
                postTitle.setText("")
                postBody.setText("")
                postTitle.requestFocus()

                Toast.makeText(this, "Post updated",Toast.LENGTH_SHORT).show();

                updateButton.isClickable = false
                updateButton.isEnabled = false
                deleteButton.isClickable = false
                deleteButton.isEnabled = false
                createButton.isClickable = true
                createButton.isEnabled = true
            }

            deleteButton.setOnClickListener {
                db.delete("POSTS", "_id = ?", arrayOf(queryResults.getString(0)))

                queryResults = db.rawQuery("SELECT * FROM POSTS", null)
                adapter.changeCursor(queryResults)
                postTitle.setText("")
                postBody.setText("")
                postTitle.requestFocus()

                Toast.makeText(this, "Post deleted",Toast.LENGTH_SHORT).show();

                updateButton.isClickable = false
                updateButton.isEnabled = false
                deleteButton.isClickable = false
                deleteButton.isEnabled = false
                createButton.isClickable = true
                createButton.isEnabled = true
            }
        }
    }
}
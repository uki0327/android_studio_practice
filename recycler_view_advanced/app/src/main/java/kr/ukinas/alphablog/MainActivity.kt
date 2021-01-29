package kr.ukinas.alphablog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ukinas.alphablog.MockDatabase.Companion.createMockData

class MainActivity : AppCompatActivity(), PostAdapter.OnPostClickListener {
    val dummyList = createMockData()
    val adapter = PostAdapter(dummyList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onEditPostClick(position: Int) {
        val clickedPost = dummyList[position]
        clickedPost.title = "Updated title"
        clickedPost.body = "Updated body"
        adapter.notifyItemChanged(position)
    }

    override fun onDeletePostClick(position: Int) {
        dummyList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
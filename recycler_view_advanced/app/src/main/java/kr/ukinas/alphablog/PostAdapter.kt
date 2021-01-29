package kr.ukinas.alphablog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val dummyData: ArrayList<Post>, val myListener: OnPostClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView), View.OnClickListener {
        val iconImage: ImageView = postView.findViewById(R.id.icon_image_view)
        val title: TextView = postView.findViewById(R.id.title)
        val body: TextView = postView.findViewById(R.id.body)
        val deleteImage: ImageView = postView.findViewById(R.id.delete_post_image)
        val editPost: ImageView = postView.findViewById(R.id.edit_post_image)

        init {
            deleteImage.setOnClickListener(this)
            editPost.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (v?.id == editPost.id) {
                myListener.onEditPostClick(position)
            } else if (v?.id == deleteImage.id) {
                myListener.onDeletePostClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
        return PostViewHolder(postView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = dummyData[position]
        holder.iconImage.setImageResource(currentPost.iconImage)
        holder.title.text = currentPost.title
        holder.body.text = currentPost.body
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    interface OnPostClickListener {
        fun onEditPostClick(position: Int)
        fun onDeletePostClick(position: Int)
    }

}
package tk.paulmburu.daggerandroid_practice.ui.main.posts


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import tk.paulmburu.daggerandroid_practice.R
import tk.paulmburu.daggerandroid_practice.models.Post


class PostsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var posts: List<Post> = ArrayList()
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        fun bind(post: Post) {
            title.text = post.title
        }

        init {
            title = itemView.findViewById(R.id.title)
        }
    }
}
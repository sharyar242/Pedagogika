package uz.texnopos.socialpedagogika.ui.bookmarks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_themes.view.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.model.Article

class BookmarksAdapter: RecyclerView.Adapter<BookmarksAdapter.BookmarkViewHolder>() {

    var models = listOf<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onBookmarkItemClick:(bkmarkId :Int,bkmarkName:String) -> Unit={ _ , _ ->}

    fun setOnBookmarkItemClickListener(onBookmarkItemClick : (bkmarkId: Int, bkmarkName:String) -> Unit) {
        this.onBookmarkItemClick = onBookmarkItemClick
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populateModel(themes: Article){
            itemView.tvTitleText.text = themes.theme

            itemView.setOnClickListener {
                onBookmarkItemClick.invoke((themes.id+itemId+1).toInt(), themes.theme)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_themes,parent,false)
        return BookmarkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int  =models.size
}
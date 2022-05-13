package uz.texnopos.socialpedagogika.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_themes.view.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.model.Title

class MainAdapter : RecyclerView.Adapter<MainAdapter.BookmarkViewHolder>() {

    var models = listOf<Title>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(title: Title) {
            itemView.tvTitleText.text = title.text
            itemView.tvTitleCard.setOnClickListener {
                onItemClick.invoke(title)
            }
        }
    }

    private var onItemClick: (title: Title) -> Unit = { _ -> }

    fun setOnItemClickListener(onItemClick: (title: Title) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_themes, parent, false)
        return BookmarkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}
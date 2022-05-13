package uz.texnopos.socialpedagogika.ui.themes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_themes.view.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.model.Article

class ThemesAdapter : RecyclerView.Adapter<ThemesAdapter.ThemesViewHolder>() {


    var models = listOf<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onThemesItemClick: (themesId: Int, themesName: Int) -> Unit = { _, _ -> }

    fun setOnThemesItemClickListener(onThemesItemClick: (themesId: Int, themesName: Int) -> Unit) {
        this.onThemesItemClick = onThemesItemClick
    }

    inner class ThemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModel(themes: Article) {
            itemView.tvTitleText.text = themes.theme
            itemView.setOnClickListener {
                onThemesItemClick.invoke(themes.id, themes.type)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_themes, parent, false)
        return ThemesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ThemesViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}
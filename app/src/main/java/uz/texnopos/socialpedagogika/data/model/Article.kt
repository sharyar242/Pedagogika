package uz.texnopos.socialpedagogika.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablica1")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "theme")
    val theme: String,
    @ColumnInfo(name = "article")
    val article :String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Int? = 0

        )
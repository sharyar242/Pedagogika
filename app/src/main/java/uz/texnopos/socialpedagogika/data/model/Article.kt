package uz.texnopos.socialpedagogika.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablica1")

class Article (

    @PrimaryKey
    val id :Int,

    @ColumnInfo(name = "type")
    val type :Int,

    @ColumnInfo(name = "main")
    val main :String,

    @ColumnInfo(name = "theme")
    val theme :String,

    @ColumnInfo(name = "article")
    val article :String

        )
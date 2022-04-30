package uz.texnopos.socialpedagogika.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.socialpedagogika.data.dao.ArticlesDao
import uz.texnopos.socialpedagogika.data.dao.MainDao
import uz.texnopos.socialpedagogika.data.dao.ThemesDao
import uz.texnopos.socialpedagogika.data.model.Article

@Database(entities =[Article::class], version = 1)

abstract class PedagogikaDatabase: RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: PedagogikaDatabase

        fun getInstance(context: Context): PedagogikaDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context,
                    PedagogikaDatabase::class.java, "kitap.db")
                    .createFromAsset("kitap.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun articleDao(): ArticlesDao
    abstract fun themesDao(): ThemesDao
    abstract fun mainDao(): MainDao
}
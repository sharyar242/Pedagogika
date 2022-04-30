package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface ThemesDao {

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    fun getAllThemes(id:Int): List<Article>


    @Query("SELECT * FROM tablica1 WHERE type=:type")
    fun getThemesByType(type :Int): List<Article>



}
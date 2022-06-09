package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface ThemesDao {

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    suspend fun getAllThemes(id:Int): List<Article>


    @Query("SELECT * FROM tablica1 WHERE type=:id")
    suspend fun getThemesByType(id :Int): List<Article>



}
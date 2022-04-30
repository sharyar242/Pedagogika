package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface MainDao {

    @Query("SELECT * FROM tablica1")
    fun getAllMain():List<Article>

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    fun getMainById(id :Int): Article
}
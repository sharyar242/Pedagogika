package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface MainDao {

    @Query("SELECT * FROM tablica1")
    suspend fun getAllMain(): List<Article>

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    suspend fun getMainById(id: Int): Article
}
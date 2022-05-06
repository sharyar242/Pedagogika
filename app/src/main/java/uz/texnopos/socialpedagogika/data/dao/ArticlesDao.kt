package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM tablica1")
    fun getAllArticles():List<Article>

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    fun getArticleById(id :Int): Article

    @Query("SELECT * FROM tablica1 WHERE is_favorite=:is_favorite")
    fun getArticleByFav(is_favorite:Int):Article

    @Update
    fun updateArticle(id: Int)

}
package uz.texnopos.socialpedagogika.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import uz.texnopos.socialpedagogika.data.model.Article

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM tablica1")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM tablica1 WHERE id=:id")
    suspend fun getArticleById(id: Int): Article

    // @Query("SELECT * FROM tablica1 WHERE is_favorite=:is_favorite")
    //fun getArticleByFav(is_favorite:Int):Article

    @Query("SELECT * FROM tablica1 WHERE is_favorite = 1")
    suspend fun getFavorites(): List<Article>

    @Update
    suspend fun updateArticle(article: Article)

}
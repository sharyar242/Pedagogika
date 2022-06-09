package uz.texnopos.socialpedagogika.ui.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.texnopos.socialpedagogika.data.dao.ArticlesDao
import uz.texnopos.socialpedagogika.data.model.Article
import kotlin.coroutines.CoroutineContext

class FavoriteListViewModel(private val articlesDao: ArticlesDao) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val _favoriteAyatList: MutableLiveData<List<Article>> = MutableLiveData()
    val favoriteAyatList: LiveData<List<Article>>
        get() = _favoriteAyatList

    fun getFavorites() {
        launch {
            getFavoriteListAsync()
        }
    }

    private suspend fun getFavoriteListAsync() {
        withContext(Dispatchers.IO) {
            _favoriteAyatList.postValue(articlesDao.getFavorites())
        }
    }

    fun removeFavorite(ayatId: Int) {
        launch {
            removeFavoriteAsync(ayatId)
        }
    }

    private suspend fun removeFavoriteAsync(id: Int) {
        withContext(Dispatchers.IO) {
            val favId = articlesDao.getArticleById(id)
            favId.isFavorite = 0
            articlesDao.updateArticle(favId)
        }
    }
}
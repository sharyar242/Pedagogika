package uz.texnopos.socialpedagogika.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.texnopos.socialpedagogika.core.SingleLiveEvent
import uz.texnopos.socialpedagogika.data.dao.ArticlesDao
import uz.texnopos.socialpedagogika.data.model.Article
import kotlin.coroutines.CoroutineContext

class ArticleListViewModel(private val articlesDao: ArticlesDao) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var _ayatList: MutableLiveData<List<Article>> = MutableLiveData()
    val ayatList: LiveData<List<Article>>
        get() = _ayatList

    private var _currentSure: MutableLiveData<Article> = MutableLiveData()
    val currentSure: LiveData<Article>
        get() = _currentSure

    private var _selectedAyat: SingleLiveEvent<Article> = SingleLiveEvent()
 //   val selectedAyat: LiveData<Article>
      //  get() = _selectedAyat

    fun getSureById(sureId: Int) {
        launch { getSureByIdAsync(sureId) }
    }

//    fun getAyatList(sureId: Int) {
//        launch { getAyatListAsync(sureId) }
//    }

    private suspend fun getSureByIdAsync(sureId: Int) {
        withContext(Dispatchers.IO) {
            _currentSure.postValue(articlesDao.getArticleById(sureId))
        }
    }

    fun setFavorite(ayatId: Int) {
        launch { setFavoriteAsync(ayatId) }
    }

    fun getSelectedAyat(ayatId: Int) {
        launch { getSelectedAyatAsync(ayatId) }
    }

    private suspend fun setFavoriteAsync(Id: Int) {
        withContext(Dispatchers.IO) {
            val id = articlesDao.getArticleById(Id)
            id.isFavorite = 1
           // ayat.sureName = currentSure.value?.name
            articlesDao.updateArticle(id)
        }
    }

    private suspend fun getSelectedAyatAsync(ayatId: Int) {
        withContext(Dispatchers.IO) {
            val ayat = articlesDao.getArticleById(ayatId)
            _selectedAyat.postValue(ayat)
        }
    }

//    private suspend fun getAyatListAsync(sureId: Int) {
//        withContext(Dispatchers.IO) {
//            _ayatList.postValue(articlesDao.getAllAyatBySureId(sureId))
//        }
//    }
}
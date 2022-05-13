package uz.texnopos.socialpedagogika.ui.kirisiw

import uz.texnopos.socialpedagogika.data.dao.ArticlesDao

class KirisiwPresenter(private val dao: ArticlesDao, private val view: KirisiwView) {
    fun getKirisiwArticle(id: Int) {
        view.setKirisiwArticle(dao.getArticleById(id))
    }
}
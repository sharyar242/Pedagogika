package uz.texnopos.socialpedagogika.ui.article

import uz.texnopos.socialpedagogika.data.dao.ArticlesDao

class ArticlePresenter(private val dao: ArticlesDao, private val view: ArticleView) {

    fun getAllArticle(id: Int) {
        view.setAllArticle(dao.getArticleById(id))
    }
}
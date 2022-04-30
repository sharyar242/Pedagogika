package uz.texnopos.socialpedagogika.ui.simpleFragment

import uz.texnopos.socialpedagogika.data.dao.ArticlesDao

class SimplePresenter(private val dao : ArticlesDao, private val view: SimpleView) {
    fun getSimpleArticle(id:Int){
        view.setSimpleArticle(dao.getArticleById(id))
    }
}
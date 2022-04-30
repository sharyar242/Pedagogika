package uz.texnopos.socialpedagogika.ui.themes.fragment

import uz.texnopos.socialpedagogika.data.dao.ThemesDao

class ThemesPresenter(private val dao: ThemesDao, private val view: ThemesView) {
    fun getThemesByType(type: Int){
        //view.setThemesType(dao.getThemesByType(type))
    }
}
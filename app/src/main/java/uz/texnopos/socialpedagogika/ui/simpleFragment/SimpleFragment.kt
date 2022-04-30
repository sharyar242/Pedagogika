package uz.texnopos.socialpedagogika.ui.simpleFragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_simple.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.Settings
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.model.Article

class SimpleFragment :Fragment(R.layout.fragment_simple), SimpleView {

    private lateinit var presenter : SimplePresenter
    private lateinit var settings: Settings


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dao = PedagogikaDatabase.getInstance(requireContext()).articleDao()
        settings = Settings(requireContext())
        presenter = SimplePresenter(dao,this)
        presenter.getSimpleArticle(91)


    }

    override fun setSimpleArticle(article: Article) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            simpleTextTitle.text = Html.fromHtml(article.article, Html.FROM_HTML_MODE_COMPACT)
            simpleTextTitle.textSize = settings.getTextSize()
        } else {
            simpleTextTitle.text = Html.fromHtml(article.article)
            simpleTextTitle.textSize = settings.getTextSize()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_actionbar_with_textsize_changer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.textSizeSettings -> {
                val view = requireActivity().findViewById<View>(R.id.textSizeSettings)
                val popupMenu = PopupMenu(requireContext(), view)
                popupMenu.menuInflater.inflate(R.menu.menu_textsize_change, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.smallTextSetting -> {
                            // preferences.edit().putFloat(TEXT_SIZE, 14f).apply()
                            //shared preference islemiy atir atip ketedi
                            simpleTextTitle.textSize = 14f
                            true
                        }
                        R.id.normalTextSetting -> {
                            //       preferences.edit().putFloat(TEXT_SIZE, 18f).apply()
                            simpleTextTitle.textSize = 18f
                            true
                        }
                        R.id.largeTextSetting -> {
                            //      preferences.edit().putFloat(TEXT_SIZE, 22f).apply()
                            simpleTextTitle.textSize = 22f
                            true
                        }
                        R.id.extraLargeTextSetting -> {
                            //    preferences.edit().putFloat(TEXT_SIZE, 26f).apply()
                            simpleTextTitle.textSize = 26f
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
                true
            }
            else -> false
        }
    }


}
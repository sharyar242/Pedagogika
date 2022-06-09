package uz.texnopos.socialpedagogika.ui.article

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_article.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.core.dp
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.dao.ArticlesDao
import uz.texnopos.socialpedagogika.data.dao.ThemesDao
import uz.texnopos.socialpedagogika.data.model.Article

class ArticleFragment : Fragment(R.layout.fragment_article), ArticleView {

    private lateinit var presenter: ArticlePresenter
    private var textList = mutableListOf<TextView>()
    private lateinit var themesDao: ThemesDao
    private var themeId: Int = -1
    private var themeType: Int = -1
    private val safeArgs: ArticleFragmentArgs by navArgs()
    private lateinit var article:Article
    private lateinit var articlesDao: ArticlesDao
    private lateinit var viewModel: ArticleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        themesDao = PedagogikaDatabase.getInstance(requireContext()).themesDao()
        themeId = safeArgs.id
        themeType = safeArgs.type.toInt()
        val dao = PedagogikaDatabase.getInstance(requireContext()).articlesDao()
        presenter = ArticlePresenter(dao, this)
        presenter.getAllArticle(themeId)
        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.bookmarksItem -> {
                    viewModel.setFavorite(id)
                   // setFavoriteArticle(requireActivity().taskId)
                    return@setOnMenuItemClickListener true
                }
                R.id.textSizeSettings -> {
                    val view = requireActivity().findViewById<View>(R.id.textSizeSettings)
                    val popupMenu = PopupMenu(requireContext(), view)
                    popupMenu.menuInflater.inflate(R.menu.menu_textsize_change, popupMenu.menu)

                    popupMenu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.smallTextSetting -> {
                                //   preferences.edit().putFloat(TEXT_SIZE, 14f).apply()
                                //shared preference islemiy atir atip ketedi
                                textView.textSize = 14f
                                true
                            }
                            R.id.normalTextSetting -> {
                                //       preferences.edit().putFloat(TEXT_SIZE, 18f).apply()
                                textView.textSize = 18f
                                true
                            }
                            R.id.largeTextSetting -> {
                                //      preferences.edit().putFloat(TEXT_SIZE, 22f).apply()
                                textView.textSize = 22f
                                true
                            }
                            R.id.extraLargeTextSetting -> {
                                //    preferences.edit().putFloat(TEXT_SIZE, 26f).apply()
                                textView.textSize = 26f
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

//    private fun setFavoriteArticle(id:Int) {
//        article.isFavorite = 1 - article.isFavorite
//        articlesDao.updateArticle(id)
//    }

    override fun setAllArticle(article: Article) {
        createDynamicViewsNamaz(article)
    }


    private lateinit var textView: TextView
    private fun createDynamicViewsNamaz(article: Article) {
        //////////////Qay jerde text qay jerde suwret ekenin tawip aliw//////////////////
        val textPair: MutableList<Pair<Int, Int>> = mutableListOf()
        val imagePair: MutableList<Pair<Int, Int>> = mutableListOf()
        var string = article.article
        var i1 = string.indexOf('{')
        var i2 = -1
        while (i1 != -1) {
            textPair.add(Pair(i2 + 1, i1 - 1))
            i2 = string.indexOf('}')
            val chars = string.toCharArray()
            chars[i1] = '*'
            chars[i2] = '*'
            string = String(chars)
            imagePair.add(Pair(i1 + 1, i2))
            i1 = string.indexOf('{')
        }
        textPair.add(Pair(i2 + 1, string.length - 1))
        /////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////
        for (i in 0 until textPair.size - 1) {
            if (textPair[i].first < textPair[i].second) {
                textView = TextView(requireContext())
                textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.times)
                textView.textSize = 18f
                textList.add(textView)
                textView.setTextColor(Color.BLACK)
                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(16.dp, 8.dp, 16.dp, 16.dp)
                textView.layoutParams = params
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textView.text =
                        Html.fromHtml(string.substring(textPair[i].first, textPair[i].second),
                            Html.FROM_HTML_MODE_COMPACT)
                } else {
                    textView.text =
                        Html.fromHtml(string.substring(textPair[i].first, textPair[i].second))
                }
                linearLayout.addView(textView)
            }
            if (imagePair[i].first < imagePair[i].second) {
                val imageView = ImageView(requireContext())
                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(getWidth() - 32.dp,
                        ((getWidth() - 32.dp) * 1.52 / 2).toInt())
                params.setMargins(16.dp, 16.dp, 16.dp, 0)
                imageView.layoutParams = params
                val id = resources.getIdentifier(string.substring(imagePair[i].first,
                    imagePair[i].second), "drawable", requireContext().packageName)
                imageView.setBackgroundResource(id)
                linearLayout.addView(imageView)
            }
        }
        val i = textPair.size - 1
        if (textPair[i].first < textPair[i].second) {
            textView = TextView(requireContext())
            textView.setTextColor(Color.BLACK)
            textView.textSize = 18f
            textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.times)
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(16.dp, 8.dp, 16.dp, 16.dp)
            textView.layoutParams = params
            textList.add(textView)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.text =
                    Html.fromHtml(string.substring(textPair[i].first, textPair[i].second),
                        Html.FROM_HTML_MODE_COMPACT)
            } else {
                textView.text =
                    Html.fromHtml(string.substring(textPair[i].first, textPair[i].second))
            }
            linearLayout.addView(textView)
        }
    }

    private fun getWidth(): Int {
        val display = Resources.getSystem().displayMetrics
        return display.widthPixels
    }

}
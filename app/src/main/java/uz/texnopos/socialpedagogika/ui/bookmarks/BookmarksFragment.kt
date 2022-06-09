package uz.texnopos.socialpedagogika.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_themes.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.dao.ThemesDao
import uz.texnopos.socialpedagogika.ui.bookmarks.adapter.BookmarksAdapter

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private var bkMarkAdapter = BookmarksAdapter()
    private lateinit var themesDao: ThemesDao
    private var themesId: Int = 1
    private lateinit var themesName: String
    private val args: BookmarksFragmentArgs by navArgs()
    private lateinit var viewModel: FavoriteListViewModel //by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        themesDao = PedagogikaDatabase.getInstance(requireContext()).themesDao()
        themesId = args.bookmarksId
        themesName = args.bookmarkString

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.getFavorites()
        themesRecyclerView.adapter = bkMarkAdapter
        setData(themesId)

//        bkMarkAdapter.setOnBookmarkItemClickListener { bkmarkId, bkmarkName ->
//            findNavController().navigate(
//                BookmarksFragmentDirections.actionBookmarksFragmentToArticleFragment(themesId = bkmarkId,
//                    themesName = bkmarkName)
//            )
//        }

    }

    private fun setData(id: Int) {
        bkMarkAdapter.models = themesDao.getThemesByType(id)
    }

}
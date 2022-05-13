package uz.texnopos.socialpedagogika.ui.themes.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_themes.*
import kotlinx.android.synthetic.main.fragment_themes.topAppBar
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.dao.ThemesDao
import uz.texnopos.socialpedagogika.ui.themes.adapter.ThemesAdapter

class ThemesFragment : Fragment(R.layout.fragment_themes) {

    private var themesAdapter = ThemesAdapter()
    private lateinit var dao: ThemesDao
    private val args: ThemesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        dao = PedagogikaDatabase.getInstance(requireContext()).themesDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.id)

        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        themesRecyclerView.adapter = themesAdapter

        themesAdapter.setOnThemesItemClickListener { id, type ->
            findNavController().navigate(
                ThemesFragmentDirections.actionThemesFragmentToArticleFragment(
                    id = id,
                    type = type.toLong())
            )
        }
    }


    private fun setData(id: Int) {
        themesAdapter.models = dao.getThemesByType(id)
    }


}
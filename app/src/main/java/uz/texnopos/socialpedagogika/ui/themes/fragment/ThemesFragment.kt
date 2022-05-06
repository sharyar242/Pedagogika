package uz.texnopos.socialpedagogika.ui.themes.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_themes.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.dao.ThemesDao
import uz.texnopos.socialpedagogika.ui.themes.adapter.ThemesAdapter

class ThemesFragment : Fragment(R.layout.fragment_themes) {

    private var themesAdapter = ThemesAdapter()
    private lateinit var dao: ThemesDao
    private var themesId: Int = 1
    private lateinit var themesName: String
    private val args: ThemesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        dao = PedagogikaDatabase.getInstance(requireContext()).themesDao()
        themesId = args.themesId
        themesName = args.themesString
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themesRecyclerView.adapter = themesAdapter
        setData(themesId)

        themesAdapter.setOnThemesItemClickListener { id, title ->

//            if (id == 1) {
//                findNavController().navigate(
//                    ThemesFragmentDirections.actionThemesFragmentToSimpleFragment(simpleId = id)
//                )
//            } else {
            findNavController().navigate(
                ThemesFragmentDirections.actionThemesFragmentToArticleFragment(
                    themesId = id,
                    themesName = title)
            )
            //  }
        }
    }


    private fun setData(id: Int) {
        themesAdapter.models = dao.getThemesByType(id)
    }


}
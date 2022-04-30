package uz.texnopos.socialpedagogika.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.PedagogikaDatabase
import uz.texnopos.socialpedagogika.data.dao.MainDao
import uz.texnopos.socialpedagogika.data.model.Article

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mainDao: MainDao

    // private lateinit var themesDao: ThemesDao
    private var models = listOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDao = PedagogikaDatabase.getInstance(requireContext()).mainDao()
        // themesDao = PedagogikaDatabase.getInstance(requireContext()).themesDao()
        models = mainDao.getAllMain()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onMainItemClick()
    }

    private fun onMainItemClick() {
        kirisiwCard.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToKirisiwFragment(1)
            )
        }
        socPedagKirisiwCard.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[1].id,
                    mainString = models[1].main)
                    //main theme bolsada islep tur
            )
        }
        ozbekistanSharayatlariCard.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[2].id,
                    mainString = models[2].main)
            )
        }
        card4.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[3].id,
                    mainString = models[3].main)
            )
        }
        card5.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[4].id,
                    mainString = models[4].main)
            )
        }
        card6.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[5].id,
                    mainString = models[5].main)
            )
        }
        card7.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[6].id,
                    mainString = models[6].main)
            )
        }
        card8.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[7].id,
                    mainString = models[7].main)
            )
        }
        card9.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[8].id,
                    mainString = models[8].main)
            )
        }
        card10.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[9].id,
                    mainString = models[9].main)
            )
        }
        card11.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[10].id,
                    mainString = models[10].main)
            )
        }
        card12.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[11].id,
                    mainString = models[11].main)
            )
        }
        card13.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[12].id,
                    mainString = models[12].main)
            )
        }
        card14.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[13].id,
                    mainString = models[13].main)
            )
        }
        card15.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[14].id,
                    mainString = models[14].main)
            )
        }
        card16.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[15].id,
                    mainString = models[15].main)
            )
        }
        card17.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToThemesFragment(
                    mainId = models[16].id,
                    mainString = models[16].main)
            )
        }
    }
}
package uz.texnopos.socialpedagogika.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.internal.MainDispatcherFactory
import uz.texnopos.socialpedagogika.R
import uz.texnopos.socialpedagogika.data.model.Title

class MainFragment : Fragment(R.layout.fragment_main) {

    private var adapter = MainAdapter()

    private val array = arrayListOf<Title>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        array.add(Title(1, getString(R.string.kirisiw)))
        array.add(Title(2,
            getString(R.string.social_pedagogikal_q_iskerlik_metodikas_mexanizmleri_h_m_texnologiyas)))
        array.add(Title(3, getString(R.string.ozberSharayatlari)))
        array.add(Title(4, getString(R.string.shet_mleketlerde)))
        array.add(Title(5, getString(R.string.zbestanda_sociall_q_pedagogikan_rawajlan_w_tariyx)))
        array.add(Title(6, getString(R.string.xal_q_pedagogikas_h_m_islam)))
        array.add(Title(7, getString(R.string.sociall_q_pedagogika)))
        array.add(Title(8,
            getString(R.string.sociall_q_pedagogika_h_m_pedagogikal_q_sociologiyada_norma)))
        array.add(Title(9,
            getString(R.string.social_pedagogt_k_siplik_iskerligi_sociall_q_pedagogikan_kategoriyalar)))
        array.add(Title(10,
            getString(R.string.sociall_q_pedagogikan_principleri_social_pedagogikal_q_izertlewler)))
        array.add(Title(11,
            getString(R.string.social_pedagogikal_q_iskerlik_metodikas_mexanizmleri_h_m_texnologiyas)))
        array.add(Title(12, getString(R.string.sha_araqlard_tipleri_h)))
        array.add(Title(13, getString(R.string.qay_rqoml_q_h_m_q_wenderlik)))
        array.add(Title(14, getString(R.string.m_h_lleler)))
        array.add(Title(15, getString(R.string.card15Text)))
        array.add(Title(16, getString(R.string.card16Text)))
        array.add(Title(17, getString(R.string.glossariy)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCardTitles.adapter = adapter
        adapter.models = array.toList()
        adapter.setOnItemClickListener {
            if (it.id == 1) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToKirisiwFragment(it.id))
            } else {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToThemesFragment(it.id)
                )
            }
        }
    }

    private fun onMainItemClick() {

    }
}
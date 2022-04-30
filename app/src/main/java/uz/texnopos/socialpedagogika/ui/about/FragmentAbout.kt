package uz.texnopos.socialpedagogika.ui.about

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_info.*
import uz.texnopos.socialpedagogika.R

class FragmentAbout:Fragment(R.layout.fragment_info) {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        preferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutBookItem.setOnClickListener {
            findNavController().navigate(
                FragmentAboutDirections.actionFragmentAboutToAboutAvtors()
            )
        }
    }
}
package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navGraphViewModels
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.presentation.MainPresenter
import com.futureyakutia.hackathon.presentation.MainView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.first_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragmentTest : NavHostFragment(), MainView {

    @Inject lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.giveText()
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun changeText(text: String) {
        first_text.text = text
    }
}

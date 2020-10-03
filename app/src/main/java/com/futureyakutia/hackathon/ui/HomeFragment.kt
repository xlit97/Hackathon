package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.presentation.MainPresenter
import com.futureyakutia.hackathon.presentation.MainView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_layout.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : MvpAppCompatFragment(), MainView {

    @Inject
    lateinit var daggerPresenter: MainPresenter

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = daggerPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        first_text.setOnClickListener { presenter.changeText() }
    }

    override fun changeText(text: String) {
        first_text.text = text
    }
}

package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.futureyakutia.hackathon.R
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment

@AndroidEntryPoint
class HelpFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_layout, container, false)
    }
}

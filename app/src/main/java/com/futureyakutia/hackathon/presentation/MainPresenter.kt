package com.futureyakutia.hackathon.presentation

import dagger.hilt.android.scopes.FragmentScoped
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
@FragmentScoped
class MainPresenter @Inject constructor() : MvpPresenter<MainView>() {

    fun changeText() {
        viewState.changeText("CHANGED!")
    }
}
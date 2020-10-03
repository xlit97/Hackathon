package com.futureyakutia.hackathon.presentation

import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : MvpPresenter<MainView>() {

    fun changeText() {
        viewState.changeText("CHANGED!")
    }
}
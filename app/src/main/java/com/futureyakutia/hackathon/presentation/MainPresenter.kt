package com.futureyakutia.hackathon.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(

) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.changeText("Checking za warudo!")
    }

    fun giveText() {
        viewState.changeText("I've gived")
    }
}
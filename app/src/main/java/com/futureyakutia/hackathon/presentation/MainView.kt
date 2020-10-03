package com.futureyakutia.hackathon.presentation

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun changeText(text: String)
}
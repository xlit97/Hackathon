package com.futureyakutia.hackathon

import android.app.Application
import com.futureyakutia.hackathon.analytics.AnalyticsManager
import com.futureyakutia.hackathon.analytics.EventsFirebase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApp : Application() {

    companion object {
        init {
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
            )
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
            )
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
            )
        }
    }

    @Inject
    lateinit var analytics: AnalyticsManager

    override fun onCreate() {
        super.onCreate()
        analytics.logEvent(EventsFirebase.APP_START)
    }
}
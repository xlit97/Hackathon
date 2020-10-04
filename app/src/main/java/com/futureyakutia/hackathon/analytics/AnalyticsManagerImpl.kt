package com.futureyakutia.hackathon.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManagerImpl(context: Context) : AnalyticsManager {

    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(event: AnalyticsEvent) {
        firebaseAnalytics.logEvent(event.eventName, null)
    }
}

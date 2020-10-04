package com.futureyakutia.hackathon.analytics

interface AnalyticsManager {
    fun logEvent(event: AnalyticsEvent)
}

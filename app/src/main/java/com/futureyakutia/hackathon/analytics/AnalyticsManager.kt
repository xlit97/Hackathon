package com.futureyakutia.hackathon.analytics

interface AnalyticsManager {

    fun logEvent(event: AnalyticsEvent) = logEvent(event, null)

    fun logEvent(event: AnalyticsEvent, params: Map<String, String>?)
}

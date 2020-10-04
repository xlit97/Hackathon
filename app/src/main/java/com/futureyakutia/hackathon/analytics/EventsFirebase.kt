package com.futureyakutia.hackathon.analytics

enum class EventsFirebase(private val value: String) : AnalyticsEvent {
    APP_START("app_start"),
    START_COMPLAIN("start_complain"),
    FINISH_COMPLAIN("finish_complain"),
    DOWNLOAD_COMPLAIN("download_complain"),
    SHARE_COMPLAIN("share_complain");

    override val eventName: String
        get() = value
}

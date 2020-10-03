package com.futureyakutia.hackathon.appeal

sealed class Answer {

    open class Choice(val text: String, val triggerAnswer: Boolean) : Answer()
    open class Write(val input: String) : Answer()
}

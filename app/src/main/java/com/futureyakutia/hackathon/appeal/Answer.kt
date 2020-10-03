package com.futureyakutia.hackathon.appeal

sealed class Answer(val text: String) {

    open class Choice(text: String, val triggerAnswer: Boolean) : Answer(text)
    open class Write(text: String, input: String) : Answer(text)
}
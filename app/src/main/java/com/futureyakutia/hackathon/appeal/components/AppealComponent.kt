package com.futureyakutia.hackathon.appeal.components

import com.futureyakutia.hackathon.appeal.clauses.LawClause

sealed class AppealComponent(val clause: LawClause) {
    data class AnimalAbuseComponent(
        var userName: String = "",
        var caseDescriptionByUser: String = "",
        var childrenWitnesses: Boolean = false,
        var commitmentDate: String = "",
        var commitmentTime: String = "",
        var commitmentPlace: String = "",
        var suspect: String = ""
    ) : AppealComponent(LawClause.AnimalAbuse)
}
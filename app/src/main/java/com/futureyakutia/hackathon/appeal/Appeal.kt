package com.futureyakutia.hackathon.appeal

import com.futureyakutia.hackathon.appeal.clauses.LawClause

class Appeal {
    var userName: String = ""
    var caseDescriptionByUser: String = ""
    var childrenWitnesses: Boolean = false
    var commitmentDate: String = ""
    var commitmentTime: String = ""
    var commitmentPlace: String = ""
    var suspect: String = ""

    val selectedIssues = hashSetOf<LawClause>()

    fun addAllIssues(issues: Collection<LawClause>) {
        selectedIssues.addAll(issues)
    }

    fun clearAllIssues() = selectedIssues.clear()
}

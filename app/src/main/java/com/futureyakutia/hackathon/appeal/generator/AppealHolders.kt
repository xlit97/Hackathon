package com.futureyakutia.hackathon.appeal.generator

enum class AppealHolders(val holder: String) {
    // Header
    POLICE_STATION("policestation"),
    OFFICER_NAME("officername"),
    USER_NAME("username"),
    USER_ADDRESS ("useraddress"),
    USER_JOB("userjob"),

    // Body
    COMMITMENT_DATE ("commitmentdate"),
    COMMITMENT_PLACE("commitmentplace"),
    COMMITMENT_TIME("commitmenttime"),
    CASE_DESCRIPTION("casedescription"),
    CHILDREN_ARE_WITNESSES("childrenarewitnesses"),
    SUSPECT("suspect"),
    LEGAL_REASONS("legalreasons"),

    // Footer
    APPEAL_DATE("appealdate"),
    SIGNATURE("signature"),
    WITNESSES_SIGNATURES("witnessessignatures")
}

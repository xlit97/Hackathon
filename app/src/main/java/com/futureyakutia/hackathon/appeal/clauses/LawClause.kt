package com.futureyakutia.hackathon.appeal.clauses

enum class LawClause(
    val id: String,
    val lawNumber: Int,
    val commonName: String,
    val lawName: String,
    val type: Type
) {
    AnimalAbuse(
        "animal_abuse",
        245,
        "Жестокое обращение с животными",
        "О жестоком обращении с животными",
        Type.CRIMINAL
    ),
    Theft(
        "theft",
        158,
        "Кража",
        "Кража",
        Type.CRIMINAL
    ),
    Fraud(
        "fraud",
        159,
        "Мошенничество",
        "Мошенничество",
        Type.CRIMINAL
    ),
    Shooting(
        "shooting",
        20,
        "Стрельба в городе",
        "Стрельба в городе",
        Type.ADMINISTRATIVE
    );

    val text
        get() = "${type.adjective} дела по статье $lawNumber ${type.shortName} Российской Федерации «${lawName}»"

    enum class Type(val shortName: String, val adjective: String) {
        CRIMINAL("УК", "уголовного"),
        ADMINISTRATIVE("КоАп", "административного"),
        CIVIL("ГК", "")
    }
}
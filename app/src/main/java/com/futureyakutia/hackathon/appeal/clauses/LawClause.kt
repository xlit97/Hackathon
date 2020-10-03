package com.futureyakutia.hackathon.appeal.clauses

sealed class LawClause(
    val id: String,
    val name: String,
    val text: String,
    val type: Type
) {
    object AnimalAbuse : LawClause(
        "animal_abuse",
        "Жестокое обращение с животными",
        "по статье 245 УК Российской Федерации «О жестоком обращении с животными»." +
                "Предупрежден (на) об ответственности по ст. 306 УК РФ, за заведомо ложный донос",
        Type.CRIMINAL
    )

    enum class Type {
        CRIMINAL,
        ADMINISTRATIVE,
        CIVIL
    }
}
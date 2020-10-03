package com.futureyakutia.hackathon.appeal

sealed class Question(val question: String, var isEnabled: Boolean = true) {

    companion object Questions{
        private const val POLICE_STATION_QUESTION = "Напишите отдел полиции в который нужно обратится"
        private const val OFFICER_NAME_QUESTION = "Введите ФИО сотрудника МВД к которому нужно обратится"
        private const val USER_NAME_QUESTION = "Введите ваше ФИО"
        private const val USER_ADDRESS = "Введите ваш адрес"
        private const val USER_JOB = "Введите ваше место работы и должность"

        private const val COMMITMENT_DATE = ""
        private const val COMMITMENT_TIME = ""
        private const val CASE_DESCRIPTION = ""
        private const val CHILDREN_ARE_WITNESSES = ""
    }

    open class WriteQuestion(question: String) : Question(question)
    open class ChooseQuestion(
        question: String,
        val answers: List<Answer> = listOf(
            Answer.Choice("Да", true),
            Answer.Choice("Нет", false)
        )
    ) : Question(question)

    // Header
    object PoliceStation : WriteQuestion(POLICE_STATION_QUESTION)
    object OfficerName : WriteQuestion(OFFICER_NAME_QUESTION)
    object UserName : WriteQuestion(USER_NAME_QUESTION)
    object UserAddress : WriteQuestion(USER_ADDRESS)
    object UserJob : WriteQuestion(USER_JOB)

    // Body
    object CommitmentDate : WriteQuestion(COMMITMENT_DATE)
    object CommitmentTime : WriteQuestion(COMMITMENT_TIME)
    object CaseDescription : WriteQuestion(CASE_DESCRIPTION)

    object ChildrenAreWitnesses : ChooseQuestion(CHILDREN_ARE_WITNESSES)

    // Footer

    // Navigation
    open class StageQuestion(
        question: String,
        val stage: Pair<Stage, Stage>
    ) : Question(question)

}
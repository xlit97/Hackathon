package com.futureyakutia.hackathon.appeal

sealed class Question(val question: String, var isEnabled: Boolean = true) {

    private companion object Questions{
        private const val POLICE_STATION_QUESTION = "Напишите отдел полиции в который нужно обратится"
        private const val OFFICER_NAME_QUESTION = "Введите ФИО сотрудника МВД к которому нужно обратится"
        private const val USER_NAME_QUESTION = "Введите ваше ФИО"
        private const val USER_ADDRESS = "Введите ваш адрес"
        private const val USER_JOB = "Введите ваше место работы и должность"

        private const val COMMITMENT_DATE = "Укажите дату проишествия(дд.мм.гггг)"
        private const val COMMITMENT_TIME = "Укажите время происшествия(чч:мм)"
        private const val COMMITMENT_PLACE = "Укажите место происшествия (адрес)"
        private const val DO_YOU_KNOW_SUSPECT = "Вы знаете злоумышленника?"
        private const val ENTER_SUSPECT_NAME = "Укажите ФИО злоумышленника (Или имя в Им. Падеже)"
        private const val CASE_DESCRIPTION = "Опишите, что знаете и видели в паре абзацев"
        private const val CHILDREN_ARE_WITNESSES = "Это было в присутствии детей?"
    }

    open class WriteQuestion(question: String) : Question(question)
    open class ChooseQuestion(
        question: String,
        val answers: List<Answer.Choice> = listOf(
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
    object CommitmentPlace : WriteQuestion(COMMITMENT_PLACE)
    object CaseDescription : WriteQuestion(CASE_DESCRIPTION)

    object ChildrenAreWitnesses : ChooseQuestion(CHILDREN_ARE_WITNESSES)

    object DoYouKnowSuspect : ChooseQuestion(DO_YOU_KNOW_SUSPECT)
    object EnterSuspectName : WriteQuestion(ENTER_SUSPECT_NAME)
    // Footer

    // Navigation
    open class StageQuestion(
        question: String,
        val stage: Pair<Stage, Stage>
    ) : Question(question)
}
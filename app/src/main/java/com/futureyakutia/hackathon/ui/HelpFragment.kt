package com.futureyakutia.hackathon.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.analytics.AnalyticsManager
import com.futureyakutia.hackathon.analytics.EventsFirebase
import com.futureyakutia.hackathon.appeal.Answer
import com.futureyakutia.hackathon.appeal.Appeal
import com.futureyakutia.hackathon.appeal.Question
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import kotlinx.android.synthetic.main.help_layout.*
import java.util.ArrayDeque
import javax.inject.Inject

@AndroidEntryPoint
class HelpFragment : MvpAppCompatFragment() {

    @Inject
    lateinit var appeal: Appeal

    @Inject
    lateinit var analytics: AnalyticsManager

    val questionsHARDCODED = ArrayDeque(
        listOf(
            Question.CommitmentDate,
            Question.CommitmentTime,
            Question.CommitmentPlace,
            Question.DoYouKnowSuspect,
            Question.EnterSuspectName,
            Question.CaseDescription,
            Question.ChildrenAreWitnesses
        )
    )

    var currentQuestion: Question? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_layout, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        help_layout_button_variant_a.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            if (mCurrentQuestion is Question.ChooseQuestion) {
                mCurrentQuestion.answers.first().also(::sendAnswer)
            }
        }
        help_layout_button_variant_b.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            if (mCurrentQuestion is Question.ChooseQuestion) {
                mCurrentQuestion.answers.last().also(::sendAnswer)
            }
        }
        help_layout_button_continue.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            if (mCurrentQuestion is Question.WriteQuestion) {
                val text = help_layout_edittext_answer.text?.toString() ?: ""
                help_layout_edittext_answer.setText("")
                val answer = Answer.Write(text)
                sendAnswer(answer)
            }
            if (mCurrentQuestion is Question.DateQuestion) {
                val text = "${datepicker.dayOfMonth}.${datepicker.month}.${datepicker.year}"
                val answer = Answer.Write(text)
                sendAnswer(answer)
            }
            if (mCurrentQuestion is Question.TimeQuestion) {
                val text = "${timepicker.hour}:${timepicker.minute}"
                val answer = Answer.Write(text)
                sendAnswer(answer)
            }
        }
        if (savedInstanceState == null) {
            val question = questionsHARDCODED.pop()
            setupQuestion(question)
        }
    }

    private fun setupQuestion(question: Question) {
        if (question.isEnabled) {
            currentQuestion = question
            help_layout_textview_question.text = question.question
            when (question) {
                is Question.ChooseQuestion -> setupChooseQuestion(question)
                is Question.WriteQuestion -> setupWriteQuestion()
                is Question.DateQuestion -> setupDateQuestion()
                is Question.TimeQuestion -> setupTimeQuestion()
            }
        } else {
            if (questionsHARDCODED.isNotEmpty()) {
                setupQuestion(questionsHARDCODED.pop())
            } else {
                navigateToShareScreen()
            }
        }
    }

    private fun setupChooseQuestion(question: Question.ChooseQuestion) {
        help_layout_edittext_answer.visibility = View.GONE
        help_layout_button_continue.visibility = View.GONE
        datepicker.visibility = View.GONE
        timepicker.visibility = View.GONE
        help_layout_linearlayout_variant_holder.visibility = View.VISIBLE
        help_layout_button_variant_a.text = question.answers.first().text
        help_layout_button_variant_b.text = question.answers.last().text
    }

    private fun setupWriteQuestion() {
        help_layout_linearlayout_variant_holder.visibility = View.GONE
        datepicker.visibility = View.GONE
        timepicker.visibility = View.GONE
        help_layout_button_continue.visibility = View.VISIBLE
        help_layout_edittext_answer.visibility = View.VISIBLE
    }

    private fun setupDateQuestion() {
        help_layout_linearlayout_variant_holder.visibility = View.GONE
        help_layout_edittext_answer.visibility = View.GONE
        timepicker.visibility = View.GONE
        datepicker.visibility = View.VISIBLE
    }

    private fun setupTimeQuestion() {
        help_layout_linearlayout_variant_holder.visibility = View.GONE
        help_layout_edittext_answer.visibility = View.GONE
        timepicker.visibility = View.VISIBLE
        datepicker.visibility = View.GONE
    }

    private fun sendAnswer(answer: Answer) {
        when (currentQuestion) {
            Question.CommitmentDate -> {
                appeal.commitmentDate = (answer as Answer.Write).input
            }
            Question.CommitmentTime -> {
                appeal.commitmentTime = (answer as Answer.Write).input
            }
            Question.CommitmentPlace -> {
                appeal.commitmentPlace = (answer as Answer.Write).input
            }
            Question.DoYouKnowSuspect -> {
                questionsHARDCODED.find { it is Question.EnterSuspectName }?.isEnabled =
                    (answer as Answer.Choice).triggerAnswer
            }
            Question.EnterSuspectName -> {
                appeal.suspect = (answer as Answer.Write).input
            }
            Question.CaseDescription -> {
                appeal.caseDescriptionByUser = (answer as Answer.Write).input
            }
            Question.ChildrenAreWitnesses -> {
                appeal.childrenWitnesses = (answer as Answer.Choice).triggerAnswer
            }
        }
        if (questionsHARDCODED.isNotEmpty()) {
            setupQuestion(questionsHARDCODED.pop())
        } else {
            navigateToShareScreen()
        }
    }

    private fun navigateToShareScreen() {
        analytics.logEvent(EventsFirebase.FINISH_COMPLAIN)
        findNavController().navigate(R.id.go_to_appeal_created)
    }
}

package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.appeal.Answer
import com.futureyakutia.hackathon.appeal.Appeal
import com.futureyakutia.hackathon.appeal.Question
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import kotlinx.android.synthetic.main.help_layout.*
import java.util.ArrayDeque

@AndroidEntryPoint
class HelpFragment : MvpAppCompatFragment() {

    val questionsHARDCODED = ArrayDeque(
        listOf(
            Question.CommitmentDate,
            Question.CommitmentPlace,
            Question.DoYouKnowSuspect,
            Question.EnterSuspectName,
            Question.CaseDescription,
            Question.ChildrenAreWitnesses
        )
    )

    var currentQuestion: Question? = null

    val appeal: Appeal = Appeal()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_layout, container, false)
    }

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
        }
    }

    override fun onStart() {
        super.onStart()
        val question = questionsHARDCODED.pop()
        setupQuestion(question)
    }

    private fun setupQuestion(question: Question) {
        if (question.isEnabled) {
            currentQuestion = question
            help_layout_textview_question.text = question.question
            when (question) {
                is Question.ChooseQuestion -> setupChooseQuestion(question)
                is Question.WriteQuestion -> setupWriteQuestion()
            }
        } else {
            if (questionsHARDCODED.isNotEmpty()) {
                setupQuestion(questionsHARDCODED.pop())
            } else {
                // todo go to sharing screen
            }
        }
    }

    private fun setupChooseQuestion(question: Question.ChooseQuestion) {
        help_layout_edittext_answer.visibility = View.GONE
        help_layout_linearlayout_variant_holder.visibility = View.VISIBLE
        help_layout_button_variant_a.text = question.answers.first().text
        help_layout_button_variant_b.text = question.answers.last().text
    }

    private fun setupWriteQuestion() {
        help_layout_linearlayout_variant_holder.visibility = View.GONE
        help_layout_edittext_answer.visibility = View.VISIBLE
    }

    private fun sendAnswer(answer: Answer) {
        when(currentQuestion) {
            Question.CommitmentDate -> {
                appeal.animalAbuse.commitmentDate = (answer as Answer.Write).input
            }
            Question.CommitmentPlace -> {
                appeal.animalAbuse.commitmentPlace = (answer as Answer.Write).input
            }
            Question.DoYouKnowSuspect -> {
                questionsHARDCODED.find { it is Question.EnterSuspectName }?.isEnabled = (answer as Answer.Choice).triggerAnswer
            }
            Question.EnterSuspectName -> {
                appeal.animalAbuse.suspect = (answer as Answer.Write).input
            }
            Question.CaseDescription -> {
                appeal.animalAbuse.caseDescriptionByUser = (answer as Answer.Write).input
            }
            Question.ChildrenAreWitnesses -> {
                appeal.animalAbuse.childrenWitnesses = (answer as Answer.Choice).triggerAnswer
            }
        }
        if (questionsHARDCODED.isNotEmpty()) {
            setupQuestion(questionsHARDCODED.pop())
        } else {
            // todo go to docx generate
        }
    }
}
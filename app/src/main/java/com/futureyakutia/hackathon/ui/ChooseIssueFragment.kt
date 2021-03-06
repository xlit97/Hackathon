package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.analytics.AnalyticsManager
import com.futureyakutia.hackathon.analytics.EventParams
import com.futureyakutia.hackathon.analytics.EventsFirebase
import com.futureyakutia.hackathon.appeal.Appeal
import com.futureyakutia.hackathon.appeal.clauses.LawClause
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.choose_issue_layout.*
import moxy.MvpAppCompatFragment
import javax.inject.Inject

@AndroidEntryPoint
class ChooseIssueFragment : MvpAppCompatFragment() {

    @Inject
    lateinit var appeal: Appeal

    @Inject
    lateinit var analytics: AnalyticsManager

    private val adapter: ChooseIssueAdapter = ChooseIssueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.choose_issue_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choose_issue_layout_recycerview_issues.layoutManager =
            GridLayoutManager(requireContext(), 2)
        choose_issue_layout_recycerview_issues.adapter = adapter
        adapter.setData(LawClause.values().toList())
        choose_issue_layout_button_continue.setOnClickListener {
            val selectedIssues = adapter.getAllSelectedItems()
            if (selectedIssues.isNotEmpty()) {
                appeal.clearAllIssues()
                appeal.addAllIssues(selectedIssues)
                trackSelectedIssues(selectedIssues)
                findNavController().navigate(R.id.go_to_questions)
            } else {
                Toast.makeText(requireContext(), "Вы не выбрали проблему", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun trackSelectedIssues(selectedIssues: List<LawClause>) {
        val params = hashMapOf(EventParams.ISSUES to selectedIssues.map { it.commonName }.toString())
        analytics.logEvent(EventsFirebase.START_COMPLAIN, params)
    }
}

package com.futureyakutia.hackathon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.appeal.clauses.LawClause

class ChooseIssueAdapter : RecyclerView.Adapter<ChooseIssueAdapter.ChooseIssueViewHolder>() {

    private var issues: List<LawClause> = emptyList()
    private val selectedIndices = hashSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseIssueViewHolder =
        ChooseIssueViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.issue_item, parent, false)
        )

    override fun getItemCount(): Int = issues.size

    override fun onBindViewHolder(holder: ChooseIssueViewHolder, position: Int) {
        holder.bind(issues[position].commonName)
    }

    fun setData(data: List<LawClause>) {
        issues = data
        notifyDataSetChanged()
    }

    fun getAllSelectedItems() = issues.filterIndexed { index, _ -> selectedIndices.contains(index) }

    inner class ChooseIssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView as TextView

        init {
            textView.setOnClickListener { view ->
                view.isActivated = !view.isActivated
                if (view.isActivated) selectedIndices.add(adapterPosition) else selectedIndices.remove(
                    adapterPosition
                )
            }
        }

        fun bind(text: String) {
            textView.text = text
        }
    }
}
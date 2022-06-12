package com.mirim.a3mcoding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.LevelProblem

class LevelProblemAdapter(val context: Context?, val problems: List<LevelProblem>?) : RecyclerView.Adapter<LevelProblemAdapter.ViewHolder>() {
    class ViewHolder(val view:View): RecyclerView.ViewHolder(view) {
        val txtProblemTitle = view.findViewById<TextView>(R.id.txt_problem_title)
        val txtLevel = view.findViewById<TextView>(R.id.txt_level)

        fun bind(problem: LevelProblem?) {
            txtProblemTitle.text = problem?.title
            txtLevel.text = LevelProblem.levelKoreanConverter(problem?.level)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_level_problem_recycler, parent, false)
        return LevelProblemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(problems?.get(position))
    }

    override fun getItemCount(): Int {
        return problems!!.size

    }
}
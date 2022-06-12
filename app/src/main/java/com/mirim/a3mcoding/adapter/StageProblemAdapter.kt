package com.mirim.a3mcoding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.StageProblem

class StageProblemAdapter(val context: Context?, val problems: List<StageProblem>?) : RecyclerView.Adapter<StageProblemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtProblem = view.findViewById<TextView>(R.id.txt_problem)

        fun bind(problem: StageProblem?) {
            txtProblem.text = ""+problem?.no + "번 - " + problem?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_stage_problem_recycler, parent, false)
        return StageProblemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(problems?.get(position))
    }

    override fun getItemCount(): Int {
        return problems!!.size
    }
}
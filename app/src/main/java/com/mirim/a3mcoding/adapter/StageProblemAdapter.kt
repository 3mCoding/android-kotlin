package com.mirim.a3mcoding.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.StageProblem
import com.mirim.a3mcoding.view.ProblemActivity

class StageProblemAdapter(val context: Context?, val problems: List<StageProblem>?) : RecyclerView.Adapter<StageProblemAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val txtProblem = view.findViewById<TextView>(R.id.txt_problem)

        fun bind(problem: StageProblem?, context: Context?) {
            txtProblem.text = ""+problem?.no + "번 - " + problem?.title

            view.setOnClickListener {
                val intent = Intent(context, ProblemActivity::class.java)
                intent.putExtra("problemType", "stage")
                intent.putExtra("no", problem?.no)
                intent.putExtra("type", problem?.type)
                context?.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_stage_problem_recycler, parent, false)
        return StageProblemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(problems?.get(position), context)
    }

    override fun getItemCount(): Int {
        return problems!!.size
    }
}
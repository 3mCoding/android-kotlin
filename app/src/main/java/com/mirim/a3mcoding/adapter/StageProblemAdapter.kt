package com.mirim.a3mcoding.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.StageProblem
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.view.ProblemActivity

class StageProblemAdapter(val context: Context?, val problems: List<StageProblem>?) : RecyclerView.Adapter<StageProblemAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtProblem = view.findViewById<TextView>(R.id.txt_problem)

        fun bind(problem: StageProblem?, context: Context?, position: Int) {
            txtProblem.text = ""+problem?.no + "번 - " + problem?.title
            if(position+1 < app.user.stage!!) {
                txtProblem.setTextColor(ContextCompat.getColor(context!!, R.color.main));
                txtProblem.setTypeface(Typeface.DEFAULT_BOLD);
            }
            else if(position+1 == app.user.stage!!) {
                txtProblem.setTypeface(Typeface.DEFAULT_BOLD);
            }

            if(position+1 <= app.user.stage!!) {
                view.setOnClickListener {
                    val intent = Intent(context, ProblemActivity::class.java)
                    intent.putExtra("problemType", "stage")
                    intent.putExtra("no", problem?.no)
                    context?.startActivity(intent)
                }
            }
            else {
                view.setOnClickListener {
                    Toast.makeText(context!!, "앞 단계를 먼저 풀어주세요!", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_stage_problem_recycler, parent, false)
        return StageProblemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(problems?.get(position), context, position)
    }

    override fun getItemCount(): Int {
        return problems!!.size
    }
}
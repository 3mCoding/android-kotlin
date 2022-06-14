package com.mirim.a3mcoding.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.LevelProblem
import com.mirim.a3mcoding.view.ProblemActivity

class LevelProblemAdapter(val context: Context?, val problems: List<LevelProblem>?) : RecyclerView.Adapter<LevelProblemAdapter.ViewHolder>() {
    class ViewHolder(val view:View): RecyclerView.ViewHolder(view) {
        val txtProblemTitle = view.findViewById<TextView>(R.id.txt_problem_title)
        val txtLevel = view.findViewById<TextView>(R.id.txt_level)

        fun bind(problem: LevelProblem?, context: Context?) {
            txtProblemTitle.text = problem?.title
            txtLevel.text = LevelProblem.levelKoreanConverter(problem?.level)
            if(problem?.solved == 1) {
                txtProblemTitle.setTextColor(ContextCompat.getColor(context!!, R.color.main));
                txtProblemTitle.setTypeface(Typeface.DEFAULT_BOLD);
                txtLevel.setTextColor(ContextCompat.getColor(context!!, R.color.main));
                txtLevel.setTypeface(Typeface.DEFAULT_BOLD);
            }

            view.setOnClickListener {
                val intent = Intent(context, ProblemActivity::class.java)
                intent.putExtra("problemType", "level")
                intent.putExtra("id", problem?.id)
                intent.putExtra("solved", problem?.solved)
                context?.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_level_problem_recycler, parent, false)
        return LevelProblemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(problems?.get(position), context)
    }

    override fun getItemCount(): Int {
        return problems!!.size

    }
}
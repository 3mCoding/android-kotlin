package com.mirim.a3mcoding.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.view.ProblemActivity

class ProblemAnswerAdapter(val context: Context, val answerCount: Int?, var answerList: HashMap<Int, String>): RecyclerView.Adapter<ProblemAnswerAdapter.ViewHolder>() {
    class ViewHolder(val view: View, var answerList: HashMap<Int, String>) : RecyclerView.ViewHolder(view) {
        val txtNumber = view.findViewById<TextView>(R.id.txt_number)
        val editAnswer = view.findViewById<EditText>(R.id.edit_answer)

        fun bind(position: Int) {
            txtNumber.text = "${position+1}."
            editAnswer.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    answerList[position] = p0.toString()
                    Log.d("ProblemAnswerAdapter", p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_answer_recycler, parent, false)
        return ProblemAnswerAdapter.ViewHolder(view, answerList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return answerCount!!
    }
}
package com.mirim.a3mcoding.adapter

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.model.User
import com.mirim.a3mcoding.model.app

class UserRecyclerAdapter(val context: Context, val users: List<User>?) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.findViewById<TextView>(R.id.txt_name)
        val txtRank = view.findViewById<TextView>(R.id.txt_rank)
        val txtProblemCount = view.findViewById<TextView>(R.id.txt_problem_count)

        fun bind(user: User?, position: Int, context: Context) {
            txtName.text = user?.student_num.toString() + " " + user?.name
            txtRank.text = ""+(position+1) + "위"
            txtProblemCount.text = ""+user?.solve_count+"개"

            if(user?.student_num == app.user.student_num) {
                Log.d("UserAdapter", "TRUE")
                txtName.setTextColor(ContextCompat.getColor(context, R.color.main));
                txtName.setTypeface(Typeface.DEFAULT_BOLD);
                txtRank.setTextColor(ContextCompat.getColor(context, R.color.main));
                txtRank.setTypeface(Typeface.DEFAULT_BOLD);
                txtProblemCount.setTextColor(ContextCompat.getColor(context, R.color.main));
                txtProblemCount.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student_rank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users?.get(position), position, context)
    }

    override fun getItemCount(): Int {
        return users!!.size
    }
}
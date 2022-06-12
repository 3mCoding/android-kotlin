package com.mirim.a3mcoding.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirim.a3mcoding.adapter.UserRecyclerAdapter
import com.mirim.a3mcoding.databinding.ActivityMainBinding
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.response.RankResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStudentList()

        binding.btnUser.setOnClickListener {
            startActivity(Intent(applicationContext, MyPageActivity::class.java))
        }

        binding.btnProblem.setOnClickListener {
            val dialog = QuestionRecommendationDialog()
            dialog.show(supportFragmentManager, "문제 추천 대화창")
        }

        binding.btnProblemAll.setOnClickListener {
            startActivity(Intent(applicationContext, ProblemAllActivity::class.java))
        }

    }

    fun getStudentList() {
        RetrofitClient.serviceAPI.userRank().enqueue(object : Callback<RankResponse> {
            override fun onResponse(call: Call<RankResponse>, response: Response<RankResponse>) {
                Log.d(TAG, response.body().toString())
                val users = response.body()?.data
                val studentAdapter = UserRecyclerAdapter(applicationContext, users)
                binding.recyclerStudent.layoutManager = LinearLayoutManager(applicationContext)
                binding.recyclerStudent.adapter = studentAdapter
            }

            override fun onFailure(call: Call<RankResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })

    }
}
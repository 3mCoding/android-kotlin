package com.mirim.a3mcoding.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirim.a3mcoding.adapter.UserRecyclerAdapter
import com.mirim.a3mcoding.databinding.ActivityMainBinding
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.response.RankResponse
import com.mirim.a3mcoding.server.response.StageProblemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        val TAG = "MainActivity"
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "OnRestart")
        getStudentList()
        getCurrentStage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getStudentList()
        getCurrentStage()

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

    fun getCurrentStage() {
        RetrofitClient.serviceAPI.getStageProblem(app.user.stage, type="0").enqueue(object : Callback<StageProblemResponse> {
            override fun onResponse(
                call: Call<StageProblemResponse>,
                response: Response<StageProblemResponse>
            ) {
                val result = response.body()
                binding.txtCurrentProblem.text = ""+result?.data?.no + "번 - " +result?.data?.title
                binding.txtCurrentProblem.setOnClickListener {
                    val intent = Intent(applicationContext, ProblemActivity::class.java)
                    intent.putExtra("problemType", "stage")
                    intent.putExtra("no", result?.data?.no)
                    intent.putExtra("type", result?.data?.type)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<StageProblemResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }
}
package com.mirim.a3mcoding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.databinding.ActivityProblemBinding
import com.mirim.a3mcoding.model.LevelProblem
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.response.LevelProblemResponse
import com.mirim.a3mcoding.server.response.StageProblemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProblemActivity : AppCompatActivity() {
    lateinit var binding: ActivityProblemBinding
    var problemType:String?= ""

    companion object {
        val TAG = "ProblemActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        problemType = intent.getStringExtra("problemType")

        when(problemType) {
            "stage" -> getStageProblem()
            "level" -> getLevelProblem()
            "recommendation" -> getRecommendationProblem()
        }

    }
    fun getStageProblem() {
        val no = intent.getIntExtra("no", 0)
        val type = intent.getIntExtra("type", 0)
        RetrofitClient.serviceAPI.getStageProblem(no, type).enqueue(object : Callback<StageProblemResponse> {
            override fun onResponse(
                call: Call<StageProblemResponse>,
                response: Response<StageProblemResponse>
            ) {
                Log.d(TAG, response.body().toString())
                if(response.raw().code() == 200) {
                    val stageProblem = response.body()?.data
                    binding.toolbar.textTitle.text = ""+stageProblem?.no + "번 - " + stageProblem?.title
                    binding.txtQuestion.text = stageProblem?.question
                    binding.txtPrint.text = stageProblem?.print
                    binding.txtCode.text = stageProblem?.code
                }
                else {
                    Toast.makeText(applicationContext, response.raw().message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StageProblemResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }

    fun getLevelProblem() {
        val id = intent.getIntExtra("id", 0)
        RetrofitClient.serviceAPI.getLevelProblem(id).enqueue(object : Callback<LevelProblemResponse> {
            override fun onResponse(
                call: Call<LevelProblemResponse>,
                response: Response<LevelProblemResponse>
            ) {
                Log.d(TAG, response.body().toString())
                if(response.raw().code() == 200) {
                    val levelProblem = response.body()?.data
                    binding.toolbar.textTitle.text = levelProblem?.title + " " + LevelProblem.levelKoreanConverter(levelProblem?.level)
                    binding.txtQuestion.text = levelProblem?.question
                    binding.txtPrint.text = levelProblem?.print
                    binding.txtCode.text = levelProblem?.code
                }
            }

            override fun onFailure(call: Call<LevelProblemResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }

    fun getRecommendationProblem() {
        val level = intent.getStringExtra("level")
        val time = intent.getIntExtra("time", 1)
        RetrofitClient.serviceAPI.getRecommendation(level, time).enqueue(object : Callback<LevelProblemResponse> {
            override fun onResponse(
                call: Call<LevelProblemResponse>,
                response: Response<LevelProblemResponse>
            ) {
                Log.d(TAG, response.body().toString())
                val levelProblem = response.body()?.data
                binding.toolbar.textTitle.text = levelProblem?.title + " " + LevelProblem.levelKoreanConverter(levelProblem?.level)
                binding.txtQuestion.text = levelProblem?.question
                binding.txtPrint.text = levelProblem?.print
                binding.txtCode.text = levelProblem?.code
            }

            override fun onFailure(call: Call<LevelProblemResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
package com.mirim.a3mcoding.view.problemFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirim.a3mcoding.adapter.LevelProblemAdapter
import com.mirim.a3mcoding.databinding.FragmentLevelProblemBinding
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.response.LevelListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LevelFragment : Fragment() {
    lateinit var binding: FragmentLevelProblemBinding

    companion object {
        val TAG = "LevelFragmet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelProblemBinding.inflate(inflater)

        getLevelProblemAll()


        return binding.root


    }

    fun getLevelProblemAll() {
        RetrofitClient.serviceAPI.getLevelList().enqueue(object: Callback<LevelListResponse> {
            override fun onResponse(
                call: Call<LevelListResponse>,
                response: Response<LevelListResponse>
            ) {
                Log.d(TAG, response.body().toString())
                val problems = response.body()?.data
                binding.recyclerLevelProblem.layoutManager = LinearLayoutManager(context)
                binding.recyclerLevelProblem.adapter = LevelProblemAdapter(context, problems)
            }

            override fun onFailure(call: Call<LevelListResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }
}
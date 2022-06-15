package com.mirim.a3mcoding.view.problemFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirim.a3mcoding.adapter.StageProblemAdapter
import com.mirim.a3mcoding.databinding.FragmentStageProblemBinding
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.response.StageListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StageFragment: Fragment() {
    lateinit var binding: FragmentStageProblemBinding

    companion object {
        val TAG = "StageFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageProblemBinding.inflate(inflater)

        getStageProblemAll()

        return binding.root
    }

    fun getStageProblemAll() {
        RetrofitClient.serviceAPI.getStageList().enqueue(object : Callback<StageListResponse> {
            override fun onResponse(
                call: Call<StageListResponse>,
                response: Response<StageListResponse>
            ) {
                Log.d(TAG, response.body().toString())
                if(response.raw().code() == 200) {
                    val problems = response.body()?.data
                    binding.recyclerStageProblem.layoutManager = LinearLayoutManager(context)
                    binding.recyclerStageProblem.adapter = StageProblemAdapter(context, problems)
                }
            }

            override fun onFailure(call: Call<StageListResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getStageProblemAll()
    }
}
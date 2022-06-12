package com.mirim.a3mcoding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.adapter.ProblemTypeFragmentAdapter
import com.mirim.a3mcoding.databinding.ActivityProblemAllBinding

class ProblemAllActivity : AppCompatActivity() {
    lateinit var binding: ActivityProblemAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProblemAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pager = binding.pager

        binding.toolbar.textTitle.text = "문제 목록"

        val tabLayout = binding.tabLayout
        pager.adapter = ProblemTypeFragmentAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "단계별로 보기"
                }
                1 -> {
                    tab.text = "난이도별로 보기"
                }
            }
        }.attach()
    }
}
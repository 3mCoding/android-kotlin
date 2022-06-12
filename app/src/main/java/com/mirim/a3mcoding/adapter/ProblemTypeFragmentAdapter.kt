package com.mirim.a3mcoding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mirim.a3mcoding.view.problemFragment.LevelFragment
import com.mirim.a3mcoding.view.problemFragment.StageFragment

class ProblemTypeFragmentAdapter(val supportFragmentManager: FragmentManager, val lifecycle: Lifecycle) : FragmentStateAdapter(supportFragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> StageFragment()
            else -> LevelFragment()
        }
    }

}
package com.example.androidhealth.ui.details.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidhealth.ui.details.steps.StepsDetailsFragment

class DetailsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return StepsDetailsFragment()
        }
        return SampleFragment()
    }
}

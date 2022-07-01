package com.example.androidhealth.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.androidhealth.R

fun Fragment.openFullScreen(directions: NavDirections) {
    requireActivity().findNavController(R.id.nav_host_activity_main).navigate(directions)
}
package com.example.androidhealth.domain

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.androidhealth.R

enum class PulseInfoType(
    @StringRes val titleResId: Int,
    @ColorRes val colorResId: Int
) {
    CALM(
        titleResId = R.string.pulse_calm,
        colorResId = R.color.errigal_white
    ),
    LIGHT_ACTIVITY(
        titleResId = R.string.pulse_light_activity,
        colorResId = R.color.jovial_jade
    ),
    FAT_BURNING(
        titleResId = R.string.pulse_fat_burning,
        colorResId = R.color.hollandaise
    ),
    AEROBIC(
        titleResId = R.string.pulse_aerobic,
        colorResId = R.color.corona
    ),
    ANAEROBIC(
        titleResId = R.string.pulse_anaerobic,
        colorResId = R.color.apocalyptic_orange
    ),
    MAXIMAL_OXYGEN_CONSUMPTION(
        titleResId = R.string.pulse_maximal_oxygen_consumption,
        colorResId = R.color.radical_red
    )
}
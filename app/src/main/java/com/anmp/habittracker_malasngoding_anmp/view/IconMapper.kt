package com.anmp.habittracker_malasngoding_anmp.view

import com.anmp.habittracker_malasngoding_anmp.R

fun iconNameToRes(icon: String): Int = when (icon.trim().lowercase()) {
    "fitness" -> R.drawable.ic_fitness
    "water" -> R.drawable.water
    "meditation" -> R.drawable.ic_meditasi
    "study" -> R.drawable.ic_book
    else -> R.drawable.water
}
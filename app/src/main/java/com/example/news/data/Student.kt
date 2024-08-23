package com.example.news.data

import com.example.news.R

data class Student(
    val name: String,
    val photo: Int
)

val students = listOf(
    Student("Luis Antonio López Lara", R.drawable.user),
    Student("Luis Fernando Guillén González", R.drawable.user2),
)
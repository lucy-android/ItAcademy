package com.example.myapplication

fun generateQuizItems(): HashMap<Int, QuizItem> {
    return hashMapOf(
        1 to QuizItem("Вы красивый?", true),
        2 to QuizItem("Вы умный?", false),
        3 to QuizItem("Вы добрый?", true)
    )
}
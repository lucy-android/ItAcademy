package com.example.myapplication

class DataUtils {
    companion object {
        var counter = 0

        fun generateQuizItems(): HashMap<Int, QuizItem> {
            return hashMapOf(
                1 to QuizItem("Вы красивый?", true),
                2 to QuizItem("Вы умный?", false),
                3 to QuizItem("Вы добрый?", true),
                4 to QuizItem("Вы скромный?", false),
                5 to QuizItem("Вы обаятельный?", true)
            )
        }
    }
}
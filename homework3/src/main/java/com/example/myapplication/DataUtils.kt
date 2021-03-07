package com.example.myapplication

class DataUtils {
    companion object {
        var counter = 0
        var resultScore = 0

        fun generateQuizItems(): HashMap<Int, QuizItem> {
            return hashMapOf(
                1 to QuizItem("Правильный ответ да", true),
                2 to QuizItem("Правильный ответ нет", false),
                3 to QuizItem("Правильный ответ да", true),
                4 to QuizItem("Правильный ответ нет", false)
            )
        }
    }
}


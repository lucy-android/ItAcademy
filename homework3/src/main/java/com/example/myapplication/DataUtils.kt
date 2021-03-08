package com.example.myapplication

class DataUtils {
    companion object {
        var counter = 1
        var resultScore = 0

        fun generateQuiz(): HashMap<Int, QuizItem> {
            return hashMapOf(
                1 to QuizItem("Есть стопка монет высотой с Эйфелеву башню. Поместятся ли эти монеты в вашей комнате?", true),
                2 to QuizItem("Правда ли, что Земля падает на Солнце", true),
                3 to QuizItem("Правда ли, что язык программирования Котлин назван в честь острова в Персидском заливе", false),
                4 to QuizItem("Правильный ответ нет", false)
            )
        }
    }
}


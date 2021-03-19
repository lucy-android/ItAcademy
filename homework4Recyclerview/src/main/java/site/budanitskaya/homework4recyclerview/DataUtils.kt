package site.budanitskaya.homework4recyclerview

import android.graphics.Color
import android.util.Log
import java.util.*
import java.util.Collections.shuffle

class DataUtils {
    companion object {

        var score = 0

        val EXTRA_MESSAGE = "site.budanitskaya.homework4recyclerview.MESSAGE"

        var mutable_colorsList = generateColorList(5999)

        val colorsList = Collections.unmodifiableList(mutable_colorsList)

        private fun generateColorList(quantity: Int): MutableList<Int> {

            var list = mutableListOf<Int>()

            for (i in 0 until quantity) {
                val color: Int =
                    generateRandomColor()
                list.add(color)
            }

            return list
        }

        private fun generateRandomColor(): Int {
            val rnd = Random()
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }

        fun generateColorQuestion(): ColorQuestion {

            var questions = generateColorList(6)

            val answer = questions[0]
            shuffle(questions)

            return ColorQuestion(questions, answer)

        }
    }
}
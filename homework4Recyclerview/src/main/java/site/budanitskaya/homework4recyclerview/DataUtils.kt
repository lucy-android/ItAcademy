package site.budanitskaya.homework4recyclerview

import android.graphics.Color
import java.util.*
import java.util.Collections.shuffle

class DataUtils {

    companion object {

        var score = 0

        const val INTENT_FLAG = "site.budanitskaya.homework4recyclerview.MESSAGE"

        var mutableColorsList = generateColorList(5999)

        val colorsList: MutableList<Int> = Collections.unmodifiableList(mutableColorsList)

        private fun generateColorList(quantity: Int): MutableList<Int> {
            val list = mutableListOf<Int>()

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

            val answerKeys = generateColorList(6)
            val answer = answerKeys[0]
            shuffle(answerKeys)

            return ColorQuestion(answerKeys, answer)

        }
    }
}
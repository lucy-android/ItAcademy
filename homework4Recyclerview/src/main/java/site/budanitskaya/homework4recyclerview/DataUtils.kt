package site.budanitskaya.homework4recyclerview

import android.graphics.Color
import java.util.*

class DataUtils{
    companion object{

        val EXTRA_MESSAGE = "site.budanitskaya.homework4recyclerview.MESSAGE"

        var mutable_colorsList = generateColorList()

        val colorsList = Collections.unmodifiableList(mutable_colorsList)

        private fun generateColorList(): MutableList<Int> {

            var list = mutableListOf<Int>()

            for(i in 0..599){
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
    }
}
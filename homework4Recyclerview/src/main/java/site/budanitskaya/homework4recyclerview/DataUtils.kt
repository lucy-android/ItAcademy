package site.budanitskaya.homework4recyclerview

import android.graphics.Color
import java.util.*

class DataUtils{
    companion object{

        fun generateRandomColors(): MutableList<Int> {

            val rnd = Random()

            var list = mutableListOf<Int>()

            for(i in 0..599){
                val color: Int =
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                list.add(color)
            }

            return list
        }
    }


}
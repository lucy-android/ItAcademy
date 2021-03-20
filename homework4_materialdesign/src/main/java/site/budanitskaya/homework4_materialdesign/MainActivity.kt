package site.budanitskaya.homework4_materialdesign


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val drawableList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawableList.add(0, R.drawable.balance)
        drawableList.add(1, R.drawable.fight)
        drawableList.add(2, R.drawable.ginger)
        drawableList.add(3, R.drawable.kanapka)
        drawableList.add(4, R.drawable.mastered_dzen)
        drawableList.add(5, R.drawable.on_the_floor)
        drawableList.add(6, R.drawable.pumik_plus_stasenka)
        drawableList.add(7, R.drawable.slippers)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.suppressLayout(true)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        recyclerView.adapter = FigureAdapter(drawableList,
            onClick = {
                startActivity(
                    FigureActivity.getStartIntent(
                        context = this,
                        figure = it
                    )
                )
            }
        )

        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
    }
}
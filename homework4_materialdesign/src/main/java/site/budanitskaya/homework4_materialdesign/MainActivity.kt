package site.budanitskaya.homework4_materialdesign


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val drawableList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable: Drawable = ResourcesCompat.getDrawable(resources, R.drawable.balance, null)!!

        drawableList.add(0, R.drawable.balance)

        val image = findViewById<ImageButton>(R.id.image)
        image.background = drawable
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
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
    }
}
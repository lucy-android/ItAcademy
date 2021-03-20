package site.budanitskaya.homework4_materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        recyclerView.adapter = FigureAdapter(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
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
package site.budanitskaya.homework4_materialdesign

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FigureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figure)

        val i = intent.getStringExtra(EXTRA_COLOR) as String

        findViewById<TextView>(R.id.activity_figure).text = i
    }

    companion object {

        private const val EXTRA_COLOR = "EXTRA_COLOR"

        fun getStartIntent(context: Context, figure: Int) =
            Intent(context, FigureActivity::class.java).apply {
                putExtra(EXTRA_COLOR, figure.toString())
            }
    }
}
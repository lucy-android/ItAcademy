package site.budanitskaya.homework4recyclerview

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.EXTRA_MESSAGE

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val colorView = findViewById<View>(R.id.colorView)
        colorView.setBackgroundColor(Color.parseColor(message))

    }
}
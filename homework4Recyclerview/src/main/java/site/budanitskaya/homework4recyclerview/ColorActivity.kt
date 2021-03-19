package site.budanitskaya.homework4recyclerview

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.EXTRA_MESSAGE
import site.budanitskaya.homework4recyclerview.databinding.ActivityColorBinding
import site.budanitskaya.homework4recyclerview.databinding.ActivityMainBinding

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityColorBinding>(this, R.layout.activity_color)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val colorView = binding.colorView
        colorView.setBackgroundColor(Color.parseColor(message))

    }

    companion object {
        fun createIntent(): Intent = Intent("activity1")
    }
}